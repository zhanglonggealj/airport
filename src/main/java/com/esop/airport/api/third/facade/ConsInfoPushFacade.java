package com.esop.airport.api.third.facade;

import com.esop.airport.api.third.vo.BuyNotifiy;
import com.esop.airport.api.third.vo.AddCons;
import com.esop.airport.api.third.vo.ChangeCons;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TBasMeter;

/**
 * @program: airport
 * @description: 商户信息推送
 * @author: Mr.Li
 * @create: 2019-06-14 15:30
 **/
public interface ConsInfoPushFacade {

    /**
     * 新增商户&新增电表
     * @param cons
     * @param meter
     * @return
     * @throws RuntimeException
     */
    int addConsAndMeter(TBasCons cons, TBasMeter meter) throws RuntimeException;

    /**
     * 商户拆表
     * @param meterId
     * @param consNo
     * @param residueMoney
     * @return
     * @throws RuntimeException
     */
    int delcons(Long meterId, String consNo, String residueMoney) throws RuntimeException;

    /**
     * 商户更换电表
     * @param consNo
     * @param meterId
     * @param meter
     * @return
     * @throws RuntimeException
     */
    int changeMeter(String consNo, long meterId, TBasMeter meter) throws RuntimeException;

    /**
     * 更新商户信息
     * @param consNo
     * @param newCons
     * @return
     * @throws RuntimeException
     */
    int changeCons(String consNo, AddCons.ConsMessEntity newCons) throws RuntimeException;

    /**
     * 更新电价信息
     * @param consNo
     * @param meterId
     * @param newTariffId
     * @param newTariffName
     * @return
     * @throws RuntimeException
     */
    int changePrice(String consNo, long meterId, long newTariffId, String newTariffName) throws RuntimeException;


    /**
     * 处理每日冻结电量
     * @param dataDate
     * @param batchNo
     * @return
     */
    int freezeHandler(String dataDate, Long batchNo);

    /**
     * 处理每日商户余额
     * @param dataDate
     * @param batchNo
     * @return
     */
    int residueHandler(String dataDate, Long batchNo);

    /**
     * 处理每日对账单
     * @param dataDate
     * @param batchNo
     * @return
     */
    int billHandler(String dataDate, Long batchNo);

    /**
     * 购电成功结果通知
     * @param buyNotifiy
     * @return
     */
    int buyNotifiy(BuyNotifiy buyNotifiy);

}
