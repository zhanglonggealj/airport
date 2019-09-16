package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TBasTextPush;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-12 08:48
 **/
public interface TextPushService {

    /**
     * 根据状态查询 推送消息相关信息
     * @param status
     * @return
     */
    List<TBasTextPush> findTextPushListByStatus(Integer status);

    /**
     * 根据id 更新待推送信息状态
     * @param id
     * @return
     */
    int updateTextPushStatusById(Long id);

    /**
     * 新增待推送数据
     * @param tBasTextPush
     * @return
     */
    int saveTextPush(TBasTextPush tBasTextPush);

}
