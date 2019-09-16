package com.esop.airport.domain.mapper;

import com.esop.airport.common.BaseMapper;
import com.esop.airport.domain.model.TBasMeter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-12 13:39
 **/
public interface MeterMapper extends BaseMapper<TBasMeter> {

    @Update("UPDATE t_bas_meter SET purchase_count = purchase_count + 1 WHERE id = #{id} AND purchase_count = #{purchaseCount}")
    int updatePurchaseCount(@Param("id") Long id, @Param("purchaseCount") Integer purchaseCount);

    @Update("UPDATE t_bas_meter SET use_prepay_flag = 1 , use_carry_flag = 1 , use_addition_flag = 1 WHERE id = #{id}")
    int updateUseFlag(@Param("id") Long id);
}


