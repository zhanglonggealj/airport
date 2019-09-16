package com.esop.airport.schedule.facade;

import com.esop.airport.common.MyTransactionExecption;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-12 09:03
 **/
public interface ThreeDataFacade {

    /**
     * 给三方平台进行推送数据
     * @return
     */
    int sendData(Long textId, int type, String data) throws MyTransactionExecption;
}
