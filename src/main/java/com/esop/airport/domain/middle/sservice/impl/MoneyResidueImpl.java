package com.esop.airport.domain.middle.sservice.impl;

import com.esop.airport.domain.middle.smapper.MoneyResidueMapper;
import com.esop.airport.domain.middle.smodel.TDayMoneyResidue;
import com.esop.airport.domain.middle.sservice.MoneyResidueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-24 09:03
 **/
@Service
public class MoneyResidueImpl implements MoneyResidueService {

    @Autowired
    MoneyResidueMapper moneyResidueMapper;

    @Override
    public List<TDayMoneyResidue> findMoneyResiduesByBatchNo(Long batchNo) {

        TDayMoneyResidue find = new TDayMoneyResidue();
        find.setBatchNo(batchNo);

        return moneyResidueMapper.select(find);
    }
}
