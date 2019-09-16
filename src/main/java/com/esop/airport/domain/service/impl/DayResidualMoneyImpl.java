package com.esop.airport.domain.service.impl;

import com.esop.airport.domain.mapper.DayResidualMoneyMapper;
import com.esop.airport.domain.model.TDayResidualMoney;
import com.esop.airport.domain.service.DayResidualMoneyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-11 13:29
 **/
@Service
public class DayResidualMoneyImpl implements DayResidualMoneyService {


    @Autowired
    DayResidualMoneyMapper dayResidualMoneyMapper;

    @Override
    public TDayResidualMoney findMoneyLast(String consNo) {

        Example example = new Example(TDayResidualMoney.class);
        example.createCriteria().andEqualTo("consNo", consNo);

//        example.orderBy("dataDate").desc();  按照时间排序

        example.setOrderByClause("col_time desc,id desc");//为什么要返回第一个数据，因为这个表的数据是不会删除的。是按照时间插入最新的

        PageHelper.startPage(1, 1, false);

        List<TDayResidualMoney> list = dayResidualMoneyMapper.selectByExample(example);

        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int saveDayResidualMoney(TDayResidualMoney tDayResidualMoney) {

        return dayResidualMoneyMapper.insertSelective(tDayResidualMoney);
    }

    @Override
    public int saveDayResidualMoneyList(List<TDayResidualMoney> list) {
        return dayResidualMoneyMapper.insertList(list);
    }

    @Override
    public List<TDayResidualMoney> findListByNowDate() {
        return dayResidualMoneyMapper.findListByNowDate();
    }
}
