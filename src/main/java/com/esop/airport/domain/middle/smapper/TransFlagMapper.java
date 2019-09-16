package com.esop.airport.domain.middle.smapper;

import com.esop.airport.common.BaseMapper;
import com.esop.airport.domain.middle.smodel.TDayTransFlag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-21 17:41
 **/
public interface TransFlagMapper extends BaseMapper<TDayTransFlag> {


    @Update("UPDATE t_day_trans_flag SET read_flag = '1', read_time = now() WHERE id = #{id}")
    int updateReadFlag(@Param("id") Long id);
}
