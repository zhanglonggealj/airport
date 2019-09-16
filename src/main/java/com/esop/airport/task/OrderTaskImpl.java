package com.esop.airport.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esop.airport.common.MyTransactionExecption;
import com.esop.airport.domain.model.TBasMeter;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.MeterService;
import com.esop.airport.domain.service.PurchaseOrderService;
import com.esop.airport.utils.OkHttpManager;
import com.esop.airport.utils.TimeUtils;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-22 15:39
 **/
@Component
public class OrderTaskImpl implements OrderTask{
    Logger log = LoggerFactory.getLogger(OrderTaskImpl.class);

    @Autowired
    MeterService meterService;
    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Transactional(value = "airportTransactionManager")
    @Async
    public void sendOrder (TPurchaseOrder order) {

        if (order == null || order.getTransactionId() == null)
        {
            return;
        }
        //组装传给腾龙那边的数据。
        JSONObject reqJson = new JSONObject();
        reqJson.put("purchaseId", order.getOrderId());//订单id
        reqJson.put("chargeId", order.getTransactionId());//微信服务器返回来的id
        reqJson.put("consNo", order.getConsNo());//商户号码
        reqJson.put("meterId", order.getMeterId());//电表的id

        TBasMeter meter = meterService.findMeterByConsNo(order.getConsNo());

        int prepay = 0;

        boolean isUpdateFlag = false;

        // 进行预充值的金额  进行补加扣减
        if (meter.getUsePrepayFlag() == 0) {
            prepay = meter.getInitMoney();
            isUpdateFlag = true;
        }

        BigDecimal addMoney = new BigDecimal("0.00");

        if (meter.getUseAdditionFlag() == 0) {
            addMoney = addMoney.add(meter.getAdditionMoney());
            isUpdateFlag = true;
        }

        if (isUpdateFlag) {
            int ret = meterService.updateUseFlag(meter.getId());
            if (ret <= 0) {
                log.error("电表是否扣除预付款，是否进行不加扣减 更新标志错误 订单id >>>" + order.getOrderId());
                return;
            }
        }
        long money = order.getTotalFee() / 100;
        // 购电下电变量
        double addmoney = addMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double sendMoneyD = (money - prepay + addmoney) * 100D;
        long sendMoneyL = (long)sendMoneyD;

        int ret = purchaseOrderService.updateType2AndSendMoney(order.getOrderId(), sendMoneyL);

        if (ret <= 0) {
            log.error("实际下发电量更新失败 订单id >>>" + order.getOrderId());
            return;
        }

        //TODO 这里了经过协商是传的分也单位
        reqJson.put("sendMoney", sendMoneyL);
        reqJson.put("purchaseCount", meter.getPurchaseCount());
        reqJson.put("purchaseDate", TimeUtils.getStringDate());
        reqJson.put("purchaseMoney", money);
        reqJson.put("additionMoney", addMoney);
        reqJson.put("carryMoney", new BigDecimal("0.00"));
        reqJson.put("initMoney", 0);

        //下电时候先进行购电次数++操作 如果购电失败再进行回滚
        int ret2 = meterService.updatePurchaseCount(meter.getId(), meter.getPurchaseCount());

        if (ret2 <= 0) {
            log.error("电表购电次数更新失败 订单id >>>" + order.getOrderId());
            return;
        }
//        if (1 == 1) {
//            throw new MyTransactionExecption("测试事务是否生效");http://47.92.49.75/sesa-server/api/sesa/server/bill/sendBill
//        }
        String url = "http://47.92.49.75/sesa-server/api/sesa/server/bill/sendBill";

        Response response = OkHttpManager.getInstance().postSync(url, null, reqJson.toJSONString());

        try {
            String body = response.body().string();

            JSONObject jsonObject = JSON.parseObject(body);

            String code =  jsonObject.getString("code");
            log.info("模拟下电 >>> " + reqJson.toJSONString());
            log.info("模拟下电 >>> " + jsonObject);

            if (!code.equals("200")) {
                log.error("下电失败 >>> " + "调用下电接口失败 >>> ", jsonObject);
                throw new MyTransactionExecption("下电处理失败 事务回滚");
                //更新数据库状态
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("下电失败 >>> " + "调用下电接口失败 >>> ", e.getMessage());
            throw new MyTransactionExecption("下电处理失败 事务回滚");
        }
    }
}
