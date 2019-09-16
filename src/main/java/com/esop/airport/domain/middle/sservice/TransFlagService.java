package com.esop.airport.domain.middle.sservice;

import com.esop.airport.domain.middle.smodel.TDayTransFlag;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-24 09:02
 **/
public interface TransFlagService {

    /**
     * 根据批号查询该批号数据是否被提取
     * @param batchNo
     * @return
     */
    TDayTransFlag findTransFlagByBatchNo(Long batchNo);

    /**
     * 根据id更新读取时间和更改读取标志位
     * @param id
     * @return
     */
    int updateReadFlag(Long id);
}