package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TBasUser;

/**
 * @author arvin liliqiang
 * @create 2019-05-28 08:44
 **/
public interface UserService {

    /**
     * 根据微信openid 获取用户信息
     * @param openId
     * @return
     */
    TBasUser findUserByOpenId(String openId);

    /**
     * 保存用户
     * @param user
     * @return
     */
    int saveUser(TBasUser user);

    /**
     * 根据主键id获取用户信息
     * @param id
     * @return
     */
    TBasUser findUserById(Long id);

    /**
     * 根据用户手机号和密码获取用户信息
     * @param phone
     * @param pass
     * @return
     */
    TBasUser findUserByPhonePass(String phone, String pass);

    /**
     * 根据用户手机号获取用户
     * @param phone
     * @return
     */
    TBasUser findUserByPhone(String phone);

    /**
     * 更新user
     * @param user
     * @return
     */
    int updataUser(TBasUser user);
}
