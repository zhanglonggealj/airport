package com.esop.airport.domain.mapper;

import com.esop.airport.common.BaseMapper;
import com.esop.airport.domain.model.TDayResidualMoney;
import com.esop.airport.domain.service.DayResidualMoneyService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-11 13:32
 **/
public interface DayResidualMoneyMapper extends BaseMapper<TDayResidualMoney> {


    @Select("SELECT * FROM t_day_residual_money WHERE TO_DAYS(data_date) = TO_DAYS(now())")
    List<TDayResidualMoney> findListByNowDate();

}
