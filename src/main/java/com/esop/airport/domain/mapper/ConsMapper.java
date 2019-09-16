package com.esop.airport.domain.mapper;

import com.esop.airport.common.BaseMapper;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by liliqiang on 2018/1/31.
 */
//@Mapper
public interface ConsMapper extends BaseMapper<TBasCons> {

    //todo 需要判断 商户状态
    @Select("SELECT * FROM t_bas_cons AS t WHERE t.status_code = '1' AND (t.cons_no LIKE '${consNo}' OR t.cons_name LIKE '${consName}') ")
    List<TBasCons> findCOnsByLikeConsNoConsName(@Param("consNo") String consNo, @Param("consName") String consName);


    @Select("SELECT COUNT(1) FROM t_bas_cons")
    long findConsCount();
}
