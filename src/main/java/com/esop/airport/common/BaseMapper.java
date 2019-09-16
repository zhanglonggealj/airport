package com.esop.airport.common;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.springframework.data.repository.NoRepositoryBean;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * Created by liliqiang on 2018/1/30.
 */
@NoRepositoryBean
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insertSelective(T record);
}
