package com.esop.airport.domain.middle.sservice.impl;

import com.esop.airport.domain.middle.smapper.EFreezeMapper;
import com.esop.airport.domain.middle.smodel.TDayElectricityFreeze;
import com.esop.airport.domain.middle.sservice.ElectricityFreezeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-24 09:04
 **/
@Service
public class ElectricityFreezeImpl implements ElectricityFreezeService {

    @Autowired
    EFreezeMapper eFreezeMapper;

    @Override
    public List<TDayElectricityFreeze> findFreezeListByBatchNo(Long batchNo) {

        TDayElectricityFreeze find = new TDayElectricityFreeze();
        find.setBatchNo(batchNo);

        return eFreezeMapper.select(find);
    }
}
