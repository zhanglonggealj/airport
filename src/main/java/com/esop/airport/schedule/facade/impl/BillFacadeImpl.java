package com.esop.airport.schedule.facade.impl;

import com.esop.airport.domain.model.TDayBill;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.BillService;
import com.esop.airport.domain.service.PurchaseOrderService;
import com.esop.airport.schedule.facade.BillFacade;
import com.esop.airport.utils.TimeUtils;
import com.github.binarywang.wxpay.bean.result.WxPayBillInfo;
import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-07-08 13:49
 **/
@Service
public class BillFacadeImpl implements BillFacade {

    Logger logger = LoggerFactory.getLogger(BillFacadeImpl.class);

    @Autowired
    WxPayService wxPayService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    BillService billService;

    @Override
    public int wxBill() {
        String billDate = TimeUtils.getCurrDateWithoutTime();

        try {
            WxPayBillResult wxPayBillResult = wxPayService.downloadBill(billDate, "SUCCESS", null, null);
            List<WxPayBillInfo> billInfoList = wxPayBillResult.getBillInfoList();

            if (billInfoList == null || billInfoList.size() <= 0) {
                return 1;
            }

            List<String> outTradeNoList = new ArrayList<>();

            Map<String, TPurchaseOrder> orderMap = new HashMap<>();

            Map<String, TDayBill> myBillMap = new HashMap<>();

            for (int i = 0; i < billInfoList.size(); i++) {

                outTradeNoList.add(billInfoList.get(i).getOutTradeNo());

                if (outTradeNoList.size() >= 100) {
                    handlerOrderBill(outTradeNoList, orderMap, myBillMap);
                }
                if (i == billInfoList.size() - 1) {
                    handlerOrderBill(outTradeNoList, orderMap, myBillMap);
                }
            }

            //TODO 这里可以做成多线程的方式来提高处理速度
            for (int i = 0; i < billInfoList.size(); i++) {

                WxPayBillInfo wxBill = billInfoList.get(i);

                TDayBill bill = myBillMap.get(wxBill.getOutTradeNo());

                TPurchaseOrder purchaseOrder = orderMap.get(wxBill.getOutTradeNo());

                //和微信系统进行对账
                if (purchaseOrder == null && purchaseOrder.getType() != 2) {
                    //TODO 和微信对账出现错误 这里出现的概率几乎为0 这里需要更新该订单状态 让该订单继续下电 需要和经理确认是否这样做
                    continue;
                }

                //和下电平台进行对账
                if (bill != null) {
                    bill.setWxTotalFee(new BigDecimal(wxBill.getTotalFee()));
                    bill.setPoundage(wxBill.getPoundage());
                    bill.setPoundageRate(wxBill.getPoundageRate());

                    if (bill.getPurchaseMoney().compareTo(new BigDecimal(wxBill.getTotalFee())) == 0 &&
                            bill.getPurchaseMoney().compareTo(bill.getPurchaseMoney()) == 0 &&
                            bill.getSendMoney().compareTo(purchaseOrder.getSendMoney()) == 0) {
                        bill.setBillStatus(1L);
                        bill.setWxOk(1L);
                    } else {
                        bill.setWxOk(0L);
                        bill.setBillStatus(-1L);
                    }
                    billService.updateBillStatus(bill);
                    continue;
                }

                //下电平台丢单
                bill = new TDayBill();

                bill.setWxTotalFee(new BigDecimal(wxBill.getTotalFee()));
                bill.setPoundage(wxBill.getPoundage());
                bill.setPoundageRate(wxBill.getPoundageRate());
                bill.setTransactionId(purchaseOrder.getTransactionId());
                bill.setPurchaseId(purchaseOrder.getOrderId());
                bill.setConsNo(purchaseOrder.getConsNo());
                bill.setMeterId(purchaseOrder.getMeterId());
                bill.setInitMoney(purchaseOrder.getInitMoney());
                bill.setAdditionMoney(purchaseOrder.getAdditionMoney());
                bill.setPurchaseMoney(new BigDecimal(purchaseOrder.getTotalFee() / 100));
                bill.setSendMoney(purchaseOrder.getSendMoney());
                bill.setPurchaseDate(purchaseOrder.getNotifyTime());
                bill.setWxOk(1L);
                bill.setPlatformOk(0L);
                billService.saveBill(bill);
            }


        } catch (WxPayException e) {
            e.printStackTrace();
            logger.error(" 定时任务处理失败 >>>> wxBill >>>> " + e.getMessage());
        }


        return 0;
    }

    /**
     * 将List数据 存入 Map 提示运行速度
     *
     * @param outTradeNoList
     * @param orderMap
     * @param myBillMap
     */
    private void handlerOrderBill(List<String> outTradeNoList, Map<String, TPurchaseOrder> orderMap, Map<String, TDayBill> myBillMap) {
        List<TPurchaseOrder> orders = purchaseOrderService.findOrdersByInOrderId(outTradeNoList);
        for (TPurchaseOrder order : orders) {
            orderMap.put(order.getOrderId(), order);
        }
        List<TDayBill> billList = billService.findListByPurchaseIds(outTradeNoList);
        if (billList != null && billList.size() > 0) {
            for (TDayBill bill : billList) {
                myBillMap.put(bill.getPurchaseId(), bill);
            }
        }
        outTradeNoList.clear();
    }
}
