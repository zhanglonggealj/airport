package com.esop.airport.domain.service.impl;

import com.esop.airport.domain.mapper.TextPushMapper;
import com.esop.airport.domain.model.TBasTextPush;
import com.esop.airport.domain.service.TextPushService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-12 08:48
 **/
@Service
public class TextPushImpl implements TextPushService {


    @Autowired
    TextPushMapper textPushMapper;

    @Override
    public List<TBasTextPush> findTextPushListByStatus(Integer status) {

        TBasTextPush find = new TBasTextPush();
        find.setStatus(status);

        PageHelper.startPage(1, 2000, false);

        List<TBasTextPush> list = textPushMapper.select(find);

        return list;
    }

    @Override
    public int updateTextPushStatusById(Long id) {

        return textPushMapper.updateStatusById(id);
    }

    @Override
    public int saveTextPush(TBasTextPush tBasTextPush) {
        return textPushMapper.insertSelective(tBasTextPush);
    }
}
