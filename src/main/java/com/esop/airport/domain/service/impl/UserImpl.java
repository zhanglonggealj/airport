package com.esop.airport.domain.service.impl;

import com.esop.airport.domain.mapper.UserMapper;
import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public TBasUser findUserByOpenId(String openId) {

        TBasUser find = new TBasUser();
        find.setWxOpenid(openId);
        find.setStatus(1);

        List<TBasUser> list = userMapper.select(find);

        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int saveUser(TBasUser user) {

        if (user != null) {
            user.setId(null);
        }

        return userMapper.insertSelective(user);
    }

    @Override
    public TBasUser findUserById(Long id) {

        TBasUser find = new TBasUser();
        find.setStatus(1);
        find.setId(id);
        find.setStatus(1);

        return userMapper.selectOne(find);
    }

    @Override
    public TBasUser findUserByPhonePass(String phone, String pass) {

        TBasUser find = new TBasUser();
        find.setMobilePhone(phone);
        find.setPassword(pass);

        List<TBasUser> list = userMapper.select(find);

        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public TBasUser findUserByPhone(String phone) {


        TBasUser find = new TBasUser();
        find.setMobilePhone(phone);
        find.setStatus(1);

        List<TBasUser> list = userMapper.select(find);

        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int updataUser(TBasUser user) {

        return userMapper.updateByPrimaryKeySelective(user);
    }
}
