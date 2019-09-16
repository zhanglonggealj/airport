package com.esop.airport.domain.service;


import com.esop.airport.domain.model.TDayFreeze;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-12 13:41
 **/
public interface FreezeService {


    /**
     * 根据商户consNo 获取用电详情
     * @param consNo
     * @return
     */
    List<TDayFreeze> findDayFreezeByConsNoAndCreateTime(String consNo,  Long id, String startTime, String endTime);

    /**
     * 插入记录
     * @param dayFreezes
     * @return
     */
    int saveFreeze(List<TDayFreeze> dayFreezes);

}
