package com.esop.airport.domain.middle.sservice;

import com.esop.airport.domain.middle.smodel.STDayBill;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-24 09:04
 **/
public interface SBillService {

    /**
     * 根据批号获取数据
     * @param batchNo
     * @return
     */
    List<STDayBill> findBillByBatchNo(Long batchNo);
}
