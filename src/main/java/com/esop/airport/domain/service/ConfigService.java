package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TConfig;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-27 11:30
 **/
public interface ConfigService {

    /**
     * 根据id 查询 配置
     * @param id
     * @return
     */
    TConfig findConfigById(Long id);
}
