package com.esop.airport.domain.service.impl;

import com.esop.airport.domain.mapper.ConfigMapper;
import com.esop.airport.domain.model.TConfig;
import com.esop.airport.domain.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-27 11:30
 **/
@Service
public class ConfigImpl implements ConfigService {

    @Autowired
    ConfigMapper configMapper;

    @Override
    public TConfig findConfigById(Long id) {

        TConfig find = new TConfig();

        find.setId(id);

        return configMapper.selectOne(find);
    }
}
