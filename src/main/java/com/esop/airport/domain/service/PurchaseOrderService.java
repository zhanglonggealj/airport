package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TPurchaseOrder;

import java.util.List;
import java.util.Map;

/**
 * @program: airport
 * @description: 购电单service
 * @author: Mr.Li
 * @create: 2019-06-11 13:47
 **/
public interface PurchaseOrderService {

    /**
     * 根据商户consNo 获取购电单信息
     * @param consNo
     * @return
     */
    List<TPurchaseOrder> findOrdersByConsNoAndCreateTime(String consNo, Long id, String startTime, String endTime);
    /**
     * 根据商户userId 获取购电单信息
     * @param userId
     * @return
     */
    List<TPurchaseOrder> findOrdersByUserId(Long userId, Long id, String startTime, String endTime);

    /**
     * 保存订单
     * @param order
     * @return
     */
    int saveOrder(TPurchaseOrder order);


    /**
     * 根据订单id查询订单
     * @param orderId
     * @return
     */
    TPurchaseOrder findOredrByOrderId(String orderId);


    /**
     * 根据商户去重
     * @param userId
     * @return
     */
    List<TPurchaseOrder> findOrdersByUserIdDistinct(Long userId);


    /**
     * 更新购电通知状态
     * @return
     */
    int updateType2Status(Long id, Long type2, Integer issuedType, String sendTime);

    /**
     * 批量获取订单信息
     * @param orderIds
     * @return
     */
    List<TPurchaseOrder> findOrdersByInOrderId(List<String> orderIds);

    /**
     * 获取当月购电金额单位分
     * @return
     */
    long findMonthMoney(String startDate, String endDate);


    /**
     * 获取当天的购电单数据
     * @return
     */
    List<TPurchaseOrder> dayPurchaseOrederList(String startDate, String endDate);


    /**
     * 获取本年的数据统计
     * @return
     */
    List<Map<String, Object>> findYearData(String startDate, String endDate);
    /**
     * 获取当月的数据统计
     * @return
     */
    List<Map<String, Object>> findMonthData(String startDate, String endDate);

    /**
     *更新订单状态
     * @param orderId
     * @param transactionId
     * @return
     */
    int updateType(String orderId, String transactionId);

    /**
     * 更新订单下发状态
     * @param orderId
     * @param sendMoney
     * @return
     */
    int updateType2AndSendMoney(String orderId, double sendMoney);

    /**
     * 获取已经微信下单但是没有进行下电的订单
     * @return
     */
    List<TPurchaseOrder> findBuyOrders();

}
