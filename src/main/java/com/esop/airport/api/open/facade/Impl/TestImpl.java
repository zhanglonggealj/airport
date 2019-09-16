package com.esop.airport.api.open.facade.Impl;

import com.esop.airport.api.open.facade.Test;
import com.esop.airport.domain.model.TToken;
import com.esop.airport.domain.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-11 15:01
 **/
@Service
public class TestImpl implements Test {

    @Autowired
    TokenService tokenService;

    @Transactional(value = "airportTransactionManager")
    @Override
    public void Test() {
        TToken t = new TToken();
        t.setBindId(1L);
        t.setCategory(1);
        t.setTokenCode("0000");
        tokenService.saveToken(t);

        System.out.println(tokenService.findTokenByCode("0000"));
        throw new RuntimeException("我是事物测试");
    }
}
