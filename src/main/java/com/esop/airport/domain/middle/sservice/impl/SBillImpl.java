package com.esop.airport.domain.middle.sservice.impl;

import com.esop.airport.domain.middle.smapper.SBillMapper;
import com.esop.airport.domain.middle.smodel.STDayBill;
import com.esop.airport.domain.middle.sservice.SBillService;
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
public class SBillImpl implements SBillService {

    @Autowired
    SBillMapper sBillMapper;

    @Override
    public List<STDayBill> findBillByBatchNo(Long batchNo) {

        STDayBill find = new STDayBill();
        find.setBatchNo(batchNo);

        return sBillMapper.select(find);
    }
}
