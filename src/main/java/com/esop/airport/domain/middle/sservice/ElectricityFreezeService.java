package com.esop.airport.domain.middle.sservice;

import com.esop.airport.domain.middle.smodel.TDayElectricityFreeze;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-24 09:04
 **/
public interface ElectricityFreezeService {

    /**
     * 根据批次号查询日结
     * @param batchNo
     * @return
     */
    List<TDayElectricityFreeze> findFreezeListByBatchNo(Long batchNo);
}
