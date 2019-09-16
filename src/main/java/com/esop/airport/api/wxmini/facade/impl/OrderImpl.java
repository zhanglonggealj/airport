package com.esop.airport.api.wxmini.facade.impl;

import com.esop.airport.api.wxmini.facade.OrderFacade;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.ConsService;
import com.esop.airport.domain.service.PurchaseOrderService;
import com.esop.airport.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-20 09:04
 **/

@Service
public class OrderImpl implements OrderFacade {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    ConsService consService;

    @Override
    public List<TPurchaseOrder> GetOrderList(String consNo, Long userId, Long id, String startTime, String endTime) {

        List<TPurchaseOrder> orders = null;

        if (StringUtils.isNotBlank(consNo)) {
            orders  = purchaseOrderService.findOrdersByConsNoAndCreateTime(consNo, id, startTime, endTime);
        } else {
            if (userId != null) {
                orders = purchaseOrderService.findOrdersByUserId(userId, id, startTime, endTime);
            }
        }

        if (orders == null || orders.size() <= 0) {
            return new ArrayList<>();
        }

        Set<String> consNos = new TreeSet<>();

        for (TPurchaseOrder order : orders) {
            consNos.add(order.getConsNo());
        }

        List<TBasCons> consList = consService.findConsByConsNos(consNos);

        Map<String, TBasCons> map = new HashMap<>();

        for (TBasCons cons : consList) {
            map.put(cons.getConsNo(), cons);
        }

        for (TPurchaseOrder tPurchaseOrder : orders) {

            tPurchaseOrder.setIssuedIsrun(null);
            tPurchaseOrder.setNotifyTime(null);
            tPurchaseOrder.setOpenid(null);
            tPurchaseOrder.setUserId(null);
            tPurchaseOrder.setUpdateTime(null);
            Double d = 100D;
            Double f = tPurchaseOrder.getTotalFee() / d;
            DecimalFormat df = new DecimalFormat("#.00");

            tPurchaseOrder.setTotalMoney(new BigDecimal(df.format(f)));

            TBasCons cons = map.get(tPurchaseOrder.getConsNo());
            tPurchaseOrder.setConsName(cons.getConsName());
            tPurchaseOrder.setConsAddress(cons.getConsAddress());
        }


        return orders;
    }
}
