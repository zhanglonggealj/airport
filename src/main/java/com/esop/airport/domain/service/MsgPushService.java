package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TBasMsgPush;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-27 11:31
 **/
public interface MsgPushService {


    /**
     * 根据商户号查询最后一次推送消息的记录 用于鉴别是否还需要再次推送消息
     * @param consNo
     * @return
     */
    TBasMsgPush findLastMsgByCons(String consNo);

    /**
     * 保存信息
     * @param msgPush
     * @return
     */
    int SaveMsgPush(TBasMsgPush msgPush);


    /**
     * 根据consNO 号更新消息状态
     * @param consNo
     * @param eventType
     * @return
     */
    int updateMsgPushEventType(String consNo, Integer eventType);

    /**
     * 根据id更新发送消息状态
     * @param id
     * @param status
     * @return
     */
    int updateMsgPushStatus(Long id, Long status);

    /**
     * 根据状态查询待发送的消息
     * @param status
     * @return
     */
    List<TBasMsgPush> findListByStatus(int page, Long status);

    /**
     * 批量更新msgPush 状态
     * @param id
     * @param status
     * @return
     */
//    int updateMsgPushs(Long id, Long status);

}
