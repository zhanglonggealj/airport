package com.esop.airport.api.wxmini.facade;

import com.esop.airport.domain.model.TPurchaseOrder;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-20 09:03
 **/
public interface OrderFacade {

    List<TPurchaseOrder> GetOrderList(String consNo, Long userId, Long id, String startTime, String endTime);

}
