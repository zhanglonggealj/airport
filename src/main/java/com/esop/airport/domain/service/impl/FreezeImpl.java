package com.esop.airport.domain.service.impl;

import com.esop.airport.common.ConstDef;
import com.esop.airport.domain.mapper.DayFreezeMapper;
import com.esop.airport.domain.model.TDayFreeze;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.FreezeService;
import com.esop.airport.utils.StringUtils;
import com.esop.airport.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-12 13:41
 **/
@Service
public class FreezeImpl implements FreezeService {

    @Autowired
    DayFreezeMapper dayFreezeMapper;

    @Override
    public List<TDayFreeze> findDayFreezeByConsNoAndCreateTime(String consNo, Long id, String startTime, String endTime) {
        Example example = new Example(TDayFreeze.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("status", 1)
                .andEqualTo("consNo", consNo)
                .andLessThan("id", id);

        if (StringUtils.isNotBlank(startTime, endTime)) {
            try {
                endTime = TimeUtils.dateAdd(3, endTime, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            criteria.andBetween("colTime", startTime, endTime);
        }

        example.setOrderByClause("col_time desc,id desc");


        PageHelper.startPage(1, ConstDef.PAGE_SIZE, false);

        List<TDayFreeze> list = dayFreezeMapper.selectByExample(example);

        return list;
    }

    @Override
    public int saveFreeze(List<TDayFreeze> dayFreezes) {

        return dayFreezeMapper.insertList(dayFreezes);
    }
}
