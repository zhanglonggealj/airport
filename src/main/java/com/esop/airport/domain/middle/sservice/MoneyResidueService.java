package com.esop.airport.domain.middle.sservice;

import com.esop.airport.domain.middle.smodel.TDayMoneyResidue;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-24 09:03
 **/
public interface MoneyResidueService {


    /**
     * 根据批号获取数据
     * @param batchNo
     * @return
     */
    List<TDayMoneyResidue> findMoneyResiduesByBatchNo(Long batchNo);
}
