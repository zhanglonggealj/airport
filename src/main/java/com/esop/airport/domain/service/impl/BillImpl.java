package com.esop.airport.domain.service.impl;

import com.esop.airport.domain.mapper.BillMapper;
import com.esop.airport.domain.model.TDayBill;
import com.esop.airport.domain.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-07-08 10:51
 **/
@Service
public class BillImpl implements BillService {

    @Autowired
    BillMapper billMapper;

    @Override
    public List<TDayBill> findListByPurchaseIds(List<String> purchaseIds) {

        Example example = new Example(TDayBill.class);

        example.createCriteria().andIn("purchaseId", purchaseIds);

        return billMapper.selectByExample(example);
    }

    @Override
    public int updateBillStatus(TDayBill bill) {
        return billMapper.updateByPrimaryKeySelective(bill);
    }

    @Override
    public int saveBill(TDayBill bill) {
        return billMapper.insertSelective(bill);
    }

    @Override
    public int saveBillList(List<TDayBill> bills) {
        return billMapper.insertList(bills);
    }
}
