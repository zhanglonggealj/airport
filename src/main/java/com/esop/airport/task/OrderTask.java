package com.esop.airport.task;

import com.esop.airport.domain.model.TPurchaseOrder;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-22 15:39
 **/
public interface OrderTask {
    /**
     * 异步进行购电单下发
     * @param order
     */
    void sendOrder(TPurchaseOrder order);
}
