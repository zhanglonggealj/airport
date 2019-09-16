package com.esop.airport.domain.mapper;

import com.esop.airport.common.BaseMapper;
import com.esop.airport.domain.model.TToken;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by liliqiang on 2018/1/31.
 */
//@Mapper
public interface TokenMapper extends BaseMapper<TToken> {


    @Select("SELECT * FROM t_token WHERE bind_id = #{bindId} and category = #{category}")
    List<TToken> findTokensByUserIdAndCategory(@Param("bindId") Long bindId, @Param("category") Integer category);


    @Delete("DELETE FROM t_token WHERE bind_id = #{bindId} and category = #{category}")
    int delToken(@Param("bindId") Long bindId, @Param("category") Integer category);


    @Select("SELECT * FROM t_token WHERE token_code = #{tokenCode} limit 1")
    TToken findTokenByCode(@Param("tokenCode") String tokenCode);
}
