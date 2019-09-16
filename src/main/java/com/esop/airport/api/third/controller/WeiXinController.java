package com.esop.airport.api.third.controller;

import com.esop.airport.common.BaseController;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.PurchaseOrderService;
import com.esop.airport.task.OrderTaskImpl;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.service.WxPayService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-24 11:11
 **/
@RestController
@RequestMapping("/third/weixin")
public class WeiXinController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(WeiXinController.class);

    @Autowired
    WxPayService wxPayService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    OrderTaskImpl orderTask;

    @RequestMapping("/pay_notify")
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {

        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);

            // 结果正确
            String orderId = result.getOutTradeNo();
            String tradeNo = result.getTransactionId();
            int totalFee = result.getTotalFee();//微信服务器回给的用户支付金额
            String openId = result.getOpenid();
            String attach = result.getAttach();
            logger.info("result===="+result.toString());
//            wxPayService.downloadBill()

            TPurchaseOrder oredr = purchaseOrderService.findOredrByOrderId(orderId);

            if (oredr == null) {
                return WxPayNotifyResponse.fail("订单不存在");
            }

            if (oredr.getType().equals(2)) {//微信可能回调多次吧，，
                return WxPayNotifyResponse.success("订单处理成功");
            }
            logger.info("oredr="+oredr.toString());

            logger.info("totalFee===="+totalFee);
            if (oredr.getTotalFee() != totalFee) {
                return WxPayNotifyResponse.fail("订单金额不正确");
            }

            int ret = purchaseOrderService.updateType(orderId, tradeNo);//SET type = 2, notify_time = NOW()，tradeNo根据这个微信返回的orderId更新

            oredr.setTransactionId(tradeNo);
            if (ret > 0) {//上面先更新本地的订单状态后在去调用腾龙那边的购电接口。保证自己这边不出现问题。
                orderTask.sendOrder(oredr);//异步执行购电下发
                return WxPayNotifyResponse.success("订单处理成功");//将来在这里写逻辑
            }

            return WxPayNotifyResponse.fail("更新订单支付状态错误");
        } catch (Exception e) {
            logger.error("微信回调结果异常,异常原因{}", e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
}
