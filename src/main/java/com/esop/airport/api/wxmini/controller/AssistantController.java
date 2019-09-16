package com.esop.airport.api.wxmini.controller;

import com.esop.airport.api.wxmini.facade.OrderFacade;
import com.esop.airport.common.*;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.ConsService;
import com.esop.airport.domain.service.PurchaseOrderService;
import com.esop.airport.domain.service.TokenService;
import com.esop.airport.domain.service.UserService;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-17 11:56
 **/

@RestController
@RequestMapping("/assistant")
public class AssistantController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(AssistantController.class);


    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    ConsService consService;

    @Autowired
    OrderFacade orderFacade;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    /**
     * 获取购电历史
     * @param id
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/getBuyOrderHistory", method = RequestMethod.GET)
    public JsonResult getBuyOrderHistory(Long id, String startTime, String endTime) {


        if (id == null) {
            id = Long.MAX_VALUE;
        }

        List<TPurchaseOrder> tPurchaseOrders = orderFacade.GetOrderList(null, getUser().getId(), id, startTime, endTime);


        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, tPurchaseOrders);

    }

    /**
     * 获取购电历史的商户信息
     * @return
     */
    @RequestMapping(value = "/getBuyHistory", method = RequestMethod.GET)
    public JsonResult getBuyHistory() {


        List<TPurchaseOrder> orders = purchaseOrderService.findOrdersByUserIdDistinct(getUser().getId());

        if (orders == null || orders.isEmpty()) {
            return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, new ArrayList<TPurchaseOrder>());
        }

        Set<String> consNos = new TreeSet<>();

        for (TPurchaseOrder order : orders) {
            consNos.add(order.getConsNo());
        }

        List<TBasCons> consList = consService.findConsByConsNos(consNos);

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, consList);

    }

    /**
     * 根据商户名字||商户cons模糊搜索
     * @param search
     * @return
     */
    @RequestMapping(value = "/getCons", method = RequestMethod.GET)
    public JsonResult getCons(String search) {

        if (StringUtils.isBlank(search)) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        if (SqlInjectionCheck.Check(search)) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        List<TBasCons> consList = consService.findCOnsByLikeConsNoConsName(search, search);

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, consList);

    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JsonResult logout() {

        TBasUser user = getUser();

        tokenService.findTokensByUserIdAndCategory(user.getId(), ConstDef.WX_MINI)
                .forEach(token -> tokenService.clearCacheByToken(token.getTokenCode()));

        tokenService.delToken(user.getId(), ConstDef.WX_MINI);

        user.setWxOpenid("del-" + UUID.getUUID());

        userService.updataUser(user);

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg);
    }
}
