package com.esop.airport.domain.service.impl;

import com.esop.airport.common.ConstDef;
import com.esop.airport.domain.mapper.ConsMapper;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.service.ConsService;
import com.esop.airport.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: airport
 * @description: 商户service实现类
 * @author: Mr.Li
 * @create: 2019-06-10 15:56
 **/
@Service
public class ConsImpl implements ConsService {

    @Autowired
    ConsMapper consMapper;

    @Override
    public List<TBasCons> findConsListByPhone(String phone) {

        TBasCons find = new TBasCons();
//        find.setStatusCode(ConstDef.CONS_NORMAL_STATUS);

        find.setMobilePhone(phone);

        return consMapper.select(find);
    }

    @Override
    public TBasCons findConsByconsNo(String consNo) {

        TBasCons find = new TBasCons();
        find.setConsNo(consNo);
//        find.setStatusCode(ConstDef.CONS_NORMAL_STATUS);

        List<TBasCons> list = consMapper.select(find);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int saveCons(TBasCons cons) {
        return consMapper.insertSelective(cons);
    }

    @Override
    public int updateCons(TBasCons cons) {

        return consMapper.updateByPrimaryKeySelective(cons);
    }

    @Override
    public List<TBasCons> findConsByConsNos(Iterable<String> consNos) {

        Example example = new Example(TBasCons.class);

        example.createCriteria().andIn("consNo", consNos);
//        .andEqualTo("statusCode", ConstDef.CONS_NORMAL_STATUS);
        PageHelper.startPage(1, 5, false);
        List<TBasCons> list = consMapper.selectByExample(example);

        return list;
    }

    @Override
    public List<TBasCons> findCOnsByLikeConsNoConsName(String consNo, String consName) {

        return consMapper.findCOnsByLikeConsNoConsName(StringUtils.isBlank(consNo) ? "" : consNo + "%"
                , StringUtils.isBlank(consName) ? "" : consName + "%");
    }

    @Override
    public long findConsConut() {

        return consMapper.findConsCount();
    }
}
