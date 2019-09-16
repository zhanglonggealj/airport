package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TDayResidualMoney;

import java.util.List;

/**
 * @program: airport
 * @description: 每天的商户余额情况
 * @author: Mr.Li
 * @create: 2019-06-11 13:29
 **/
public interface DayResidualMoneyService {

    /**
     * 根据商户consNo 获取最后一次更新的余额
     * @param consNo
     * @return
     */
    TDayResidualMoney findMoneyLast(String consNo);

    /**
     * 保存剩余金额
     * @param tDayResidualMoney
     * @return
     */
    int saveDayResidualMoney(TDayResidualMoney tDayResidualMoney);

    /**
     * 批量保存余额
     * @param list
     * @return
     */
    int saveDayResidualMoneyList(List<TDayResidualMoney> list);

    /**
     * 查询数据时间为今天的数据
     * @return
     */
    List<TDayResidualMoney> findListByNowDate();
}
