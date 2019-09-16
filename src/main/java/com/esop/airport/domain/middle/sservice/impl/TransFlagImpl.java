package com.esop.airport.domain.middle.sservice.impl;

import com.esop.airport.domain.middle.smapper.TransFlagMapper;
import com.esop.airport.domain.middle.smodel.TDayTransFlag;
import com.esop.airport.domain.middle.sservice.TransFlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-24 09:02
 **/
@Service
public class TransFlagImpl implements TransFlagService {

    @Autowired
    TransFlagMapper transFlagMapper;

    @Override
    public TDayTransFlag findTransFlagByBatchNo(Long batchNo) {

        TDayTransFlag find = new TDayTransFlag();
        find.setBatchNo(batchNo);

        return transFlagMapper.selectOne(find);
    }

    @Override
    public int updateReadFlag(Long id) {
        return transFlagMapper.updateReadFlag(id);
    }
}
