package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TBasMeter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-12 13:39
 **/
public interface MeterService {

    /***
     * 根据电表id查询电表信息 增加此方法是防止购电的时候 出现更换电表导致数据无法正常下发 （其实这样也无法进行正常的电力下发）
     * @param meterId
     * @return
     */
    TBasMeter findMeterByMeterId(Long meterId);

    /**
     * 根据商户号查询电表list
     * @param consNo
     * @return
     */
    List<TBasMeter> findMeterListByConsNo(List<String> consNo);

    /**
     * 根据商户no获取电表信息
     * @param consNo
     * @return
     */
    TBasMeter findMeterByConsNo(String consNo);

    /**
     * 根据商户no与电表出厂编号获取电表信息
     * @param consNo
     * @param meterMadeNo
     * @return
     */
    /*TBasMeter findMeterByConsNoAndMeterMadeNo(String consNo,String meterMadeNo);*/

    /**
     * 保存电表信息
     * @param meter
     * @return
     */
    int saveMeter(TBasMeter meter);

    /**
     * 更新电表信息
     * @param meter
     * @return
     */
    int updateMeter(TBasMeter meter);

    /**
     * 更新电表计数
     */
    int updatePurchaseCount(Long id, Integer purchaseCount);

    /**
     * 更新是否进行扣减预制金额、扣减补加、结转
     * @param id
     * @return
     */
    int updateUseFlag(Long id);

}
