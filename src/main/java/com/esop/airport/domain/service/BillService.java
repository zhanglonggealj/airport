package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TDayBill;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-07-08 10:51
 **/
public interface BillService {

    /**
     * 根据购电单获取对账单数据
     * @param purchaseIds
     * @return
     */
    List<TDayBill> findListByPurchaseIds(List<String> purchaseIds);

    /**
     * 更新对账单状态
     * @return
     */
    int updateBillStatus(TDayBill bill);

    /**
     * 保存对账单
     * @param bill
     * @return
     */
    int saveBill(TDayBill bill);

    /**
     * 批量保存bill
     * @param bills
     * @return
     */
    int saveBillList(List<TDayBill> bills);
}
