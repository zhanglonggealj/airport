package com.esop.airport.api.wxmini.controller;

import com.esop.airport.api.open.controller.OpenController;
import com.esop.airport.api.wxmini.facade.OrderFacade;
import com.esop.airport.common.BaseController;
import com.esop.airport.common.ConstDef;
import com.esop.airport.common.JsonResult;
import com.esop.airport.common.ResultDef;
import com.esop.airport.domain.model.*;
import com.esop.airport.domain.service.*;
import com.esop.airport.task.UserTask;
import com.esop.airport.utils.StringUtils;
import com.esop.airport.utils.TimeUtils;
import com.esop.airport.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @program: airport
 * @description:一般数据请求商户余额，历史用电量，商户订单
 * @author: Mr.Li
 * @create: 2019-06-10 17:14
 **/

@RestController
@RequestMapping("/general")
public class GeneralController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(GeneralController.class);


    @Autowired
    DayResidualMoneyService dayResidualMoneyService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserTask userTask;

    @Autowired
    FreezeService freezeService;

    @Autowired
    OrderFacade orderFacade;

    @Autowired
    ConsService consService;

    /**
     * 获取余额，切换商户来获取余额。 这里面的功能，如果用户没有这个手机号绑定的商户的话  就需要进行拦截，这里面的所有方法都用不了的，
     * @param consNo 这里面的方法是用来对含有商户的用户来访问使用的。因为在操作这些功能的时候，其他人可能吧这个手机号关联的商户解绑了
     * @return  不能每个方法都去判断一下数据库吧 再去进行逻辑，所以直接写到拦截器里面了 ，，不错
     */
    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public JsonResult getBalance(String consNo) {

        if (StringUtils.isBlank(consNo)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        TDayResidualMoney moneyLast = dayResidualMoneyService.findMoneyLast(consNo);//根据商户consNo 获取最后一次更新的余额

        if (moneyLast != null) {
            return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, moneyLast.getRemainMoney().toString());
        }

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, new BigDecimal("0.00").toString());
    }

    /**
     * 查询购电单
     * @param consNo
     * @param id
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBuyOrders", method = RequestMethod.GET)
    public JsonResult getBuyOrders(String consNo, Long id, String startTime, String endTime) throws Exception {

        if (StringUtils.isBlank(consNo)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        if (id == null) {
            id = Long.MAX_VALUE;
        }

        List<TPurchaseOrder> tPurchaseOrders = orderFacade.GetOrderList(consNo, null, id, startTime, endTime);

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, tPurchaseOrders);
    }

    /**
     * 查看冻结示数
     * @param consNo
     * @param id
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/getFreezes", method = RequestMethod.GET)
    public JsonResult getFreezes(String consNo, Long id, String startTime, String endTime) {

        if (StringUtils.isBlank(consNo)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        if (id == null) {
            id = Long.MAX_VALUE;
        }

        List<TDayFreeze> freezes = freezeService.findDayFreezeByConsNoAndCreateTime(consNo, id, startTime, endTime);

        if (freezes == null || freezes.isEmpty()) {
            freezes =  new ArrayList<>();
            return  new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, freezes);
        }

        Set<String> consNos = new TreeSet<>();

        for (TDayFreeze freeze : freezes) {
            consNos.add(freeze.getConsNo());
        }

        List<TBasCons> consList = consService.findConsByConsNos(consNos);

        Map<String, TBasCons> map = new HashMap<>();

        for (TBasCons cons : consList) {
            map.put(cons.getConsNo(), cons);
        }

        for (TDayFreeze freeze : freezes) {
            TBasCons cons = map.get(freeze.getConsNo());
            freeze.setBatchNo(null);
            freeze.setConsName(cons.getConsName());
            freeze.setConsAddress(cons.getConsAddress());
            freeze.setCreateTime(new Timestamp(freeze.getDataDate().getTime()));
            freeze.setColTime(new Timestamp(freeze.getDataDate().getTime()));
        }


        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, freezes);
    }



}
