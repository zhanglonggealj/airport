package com.esop.airport.domain.service.impl;

import com.esop.airport.domain.mapper.MsgPushMapper;
import com.esop.airport.domain.model.TBasMsgPush;
import com.esop.airport.domain.service.MsgPushService;
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
 * @create: 2019-06-27 11:31
 **/
@Service
public class MsgPushImpl implements MsgPushService {

    @Autowired
    MsgPushMapper msgPushMapper;


    @Override
    public TBasMsgPush findLastMsgByCons(String consNo) {

        Example example = new Example(TBasMsgPush.class);

        example.createCriteria().andEqualTo("consNo", consNo)
//                .andEqualTo("status", 2)
                .andLessThan("id", Long.MAX_VALUE);

        PageHelper.startPage(1, 1);

        List<TBasMsgPush> list = msgPushMapper.selectByExample(example);

        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int SaveMsgPush(TBasMsgPush msgPush) {
        return msgPushMapper.insertSelective(msgPush);
    }

    @Override
    public int updateMsgPushEventType(String consNo, Integer eventType) {
        return msgPushMapper.updateMsgPushEventType(consNo, eventType);
    }

    @Override
    public int updateMsgPushStatus(Long id, Long status) {
        return msgPushMapper.updateMsgPushStatus(id, status);
    }

    @Override
    public List<TBasMsgPush> findListByStatus(int page, Long status) {

        PageHelper.startPage(page, 1000);

        TBasMsgPush find = new TBasMsgPush();

        find.setStatus(status);

        return msgPushMapper.select(find);
    }

//    @Override
//    public int updateMsgPushs(Long id, Long status) {
//        return 0;
//    }
}
