package com.esop.airport.api.wxmini.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esop.airport.common.BaseController;
import com.esop.airport.common.JsonResult;
import com.esop.airport.common.ResultDef;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TBasMeter;
import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.ConsService;
import com.esop.airport.domain.service.MeterService;
import com.esop.airport.domain.service.PurchaseOrderService;
import com.esop.airport.utils.OkHttpManager;
import com.esop.airport.utils.StringUtils;
import com.esop.airport.utils.TimeUtils;
import com.esop.airport.utils.UUID;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-13 08:41
 **/
@RestController
@RequestMapping("/purchase")
public class PurchaseController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    ConsService consService;

    @Autowired
    MeterService meterService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Qualifier("wxPayService")
    @Autowired
    WxPayService wxPayService;

    @Value("${airport.weixin.notifyurl}")
    String notifyUrl;

    /**
     * 微信购电创建订单
     * @param map
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public JsonResult order(@RequestBody Map<String,String> map) {


        String consNo = map.get("consNo");
        String moneyStr = map.get("money");
        Integer money = null;

        try {
            money = Integer.parseInt(moneyStr);
        } catch (Exception e) {

        }

        //首先判断传过来的商户号存在且有对应电表。万一其他地方把这个电表与用户注销了，
        if (StringUtils.isBlank(consNo) || money == null) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        TBasCons cons = consService.findConsByconsNo(consNo);

        if (cons == null) {
            return new JsonResult(ResultDef.CERR_CONS_NOT_EXIST.code, ResultDef.CERR_CONS_NOT_EXIST.msg);
        }

        TBasMeter meter = meterService.findMeterByConsNo(consNo);

        if (meter == null) {
            return new JsonResult(ResultDef.CERR_CONS_NOT_EXIST.code, ResultDef.CERR_CONS_NOT_EXIST.msg);
        }

        if (money < 1 || money > 10000) {
            return new JsonResult(ResultDef.CERR_MONEY_EXIST.code, ResultDef.CERR_MONEY_EXIST.msg);
        }

        if (meter.getUsePrepayFlag() == 0) {//为0是需要处理的。为1不需要处理
            if (money <= meter.getInitMoney()) {
                return new JsonResult(ResultDef.CERR_USER_FIRST_BUY_ORDER.code, String.format(ResultDef.CERR_USER_FIRST_BUY_ORDER.msg, meter.getInitMoney()));
            }
        }

        String orderNo = UUID.getOrderNo();

        TBasUser user = getUser();

        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();

        request.setBody("购电");
        request.setDetail("大兴机场购电 商户：" + cons.getConsName());
        request.setOutTradeNo(orderNo);
        request.setTotalFee(money * 100);  //乘以100变成分
        // TODO
        //request.setTotalFee(money);
        request.setSpbillCreateIp("192.168.1.1");//negix get header
        request.setNotifyUrl(notifyUrl);//微信的回调地址，公网
        request.setTradeType("JSAPI");
        request.setOpenid(user.getWxOpenid());

        Object payInfo = null;
        try {
            payInfo = wxPayService.createOrder(request);
        } catch (WxPayException e) {
            e.printStackTrace();
            return new JsonResult(ResultDef.CERR.code, ResultDef.CERR.msg);
        }

        TPurchaseOrder purchaseOrder = new TPurchaseOrder();

        purchaseOrder.setTotalFee(new Long(money) * 100);//用户下单的金额
        purchaseOrder.setOpenid(user.getWxOpenid());
        purchaseOrder.setConsNo(consNo);
        purchaseOrder.setUserId(user.getId());
        purchaseOrder.setMeterId(meter.getMeterId());
        purchaseOrder.setOrderId(orderNo);
        purchaseOrder.setPayType(2L);//微信
        purchaseOrder.setType(1L);//创建订单的时候都是未付款  因为需要微信回调接口进行校验



        //模拟购电成功
        //TODO
        /*purchaseOrder.setType(2L);//成功后修改支付类型。
        purchaseOrder.setNotifyTime(new Date());//第三方支付通知时间
        String transactionId = UUID.getOrderNo();//第三发交易单号。微信给的吧？ 目前是自己写的transactionId  uuid
        purchaseOrder.setTransactionId(UUID.getOrderNo());


//        purchaseOrder.setIssuedTime(new Date());
//        purchaseOrder.setIssuedIsrun(2L);
//        purchaseOrder.setType2(2L);
        //开始下发电费//
        //TODO 需要加事物

        JSONObject reqJson = new JSONObject();
        reqJson.put("purchaseId", orderNo);
        reqJson.put("chargeId", transactionId);
        reqJson.put("consNo", consNo);
        reqJson.put("meterId", meter.getMeterId());

        int prepay = 0;

        boolean isUpdateFlag = false;

        //TODO 暂时不进行预充值的金额  进行补加扣减
        if (meter.getUsePrepayFlag() == 0) {// 0是有预支金
            prepay = meter.getInitMoney();
            //TODO 更新use标志位
            isUpdateFlag = true;
        }

        BigDecimal addMoney = new BigDecimal("0.00");

        if (meter.getUseAdditionFlag() == 0) {
            addMoney = addMoney.add(meter.getAdditionMoney());
            //TODO 更新use标志位
            isUpdateFlag = true;
        }

        if (isUpdateFlag) {//更新那三个标志。补加扣减 预支金
            int ret = meterService.updateUseFlag(meter.getId());
            //TODO 这里需要验证ret结果
        }

        //TODO 购电下电变量
        //目前结转金额不用咱们处理,处理补加扣减 跟预支金。
        double addmoney = addMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double sendMoneyD = (money - prepay + addmoney) * 100D;
        long sendMoneyL = (long)sendMoneyD;
        purchaseOrder.setSendMoney(new BigDecimal(sendMoneyL));//下发金额不是在生成订单的时候存的，是在补加扣减以后存的

        //TODO 这里了经过协商是传的分也单位
        reqJson.put("sendMoney", sendMoneyL);
        reqJson.put("purchaseCount", meter.getPurchaseCount());
        reqJson.put("purchaseDate", TimeUtils.getStringDate());
        reqJson.put("purchaseMoney", money);
        reqJson.put("additionMoney", addMoney);
        reqJson.put("carryMoney", new BigDecimal("0.00"));
        reqJson.put("initMoney", 0);
        //47.92.49.75
        String url = "http://47.92.49.75/sesa-server/api/sesa/server/bill/sendBill";

        Response response = OkHttpManager.getInstance().postSync(url, null, reqJson.toJSONString());

        try {
            String body = response.body().string();

            JSONObject jsonObject = JSON.parseObject(body);

            String code =  jsonObject.getString("code");
            logger.info("模拟下电 >>> " + reqJson.toJSONString());
            logger.info("模拟下电 >>> " + jsonObject);

            if (!code.equals("200")) {
                return new JsonResult<>(ResultDef.CERR.code, "调用下电接口失败-模拟下电结束", jsonObject);
                //更新数据库状态
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult<>(ResultDef.CERR.code, "调用下电接口失败-模拟下电结束", e.getMessage());
        }

        int ret2 = meterService.updatePurchaseCount(meter.getId(), meter.getPurchaseCount());//如果下电成功后 电表次数加1

        if (ret2 <= 0) {
            //TODO 抛出异常
        }

        //TODO 模拟结束*/

        int ret = purchaseOrderService.saveOrder(purchaseOrder);//下单  订单先入库。type2位1（）

        if (ret > 0) {
            JSONObject json = new JSONObject();
            json.put("orderId", orderNo);
            json.put("payInfo", payInfo);
            return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, json);
        } else {
            return new JsonResult(ResultDef.CERR.code, ResultDef.CERR.msg);
        }
    }


    /**
     * 获取购电单详情
     * @param oredrId
     * @return
     */
    @RequestMapping(value = "/getOrderStstus", method = RequestMethod.GET)
    public JsonResult getOrderStstus(String oredrId) {

        if (StringUtils.isBlank(oredrId)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        TPurchaseOrder oredr = purchaseOrderService.findOredrByOrderId(oredrId);

        if (oredr == null) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        TBasCons cons = consService.findConsByconsNo(oredr.getConsNo());

        if (cons == null) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        TBasMeter meter = meterService.findMeterByConsNo(oredr.getConsNo());

        oredr.setIssuedIsrun(null);
        oredr.setOpenid(null);
        oredr.setUserId(null);
        oredr.setUpdateTime(null);
        Double d = 100D;
        Double f = oredr.getTotalFee() / d;
        DecimalFormat df = new DecimalFormat("#.00");
        oredr.setTotalMoney(new BigDecimal(df.format(f)));
        oredr.setConsAddress(cons.getConsAddress());
        oredr.setConsName(cons.getConsName());
        oredr.setMeterMadeNo(meter.getMeterMadeNo());
        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, oredr);

    }
}
