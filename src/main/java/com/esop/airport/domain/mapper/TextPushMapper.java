package com.esop.airport.domain.mapper;

import com.esop.airport.common.BaseMapper;
import com.esop.airport.domain.model.TBasTextPush;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-12 08:47
 **/
public interface TextPushMapper extends BaseMapper<TBasTextPush> {


    @Update("UPDATE t_bas_text_push SET status = 1 WHERE id = #{id} AND status = 0")
    int updateStatusById(@Param("id") Long id);

}
