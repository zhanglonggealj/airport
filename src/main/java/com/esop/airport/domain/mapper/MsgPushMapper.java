package com.esop.airport.domain.mapper;

import com.esop.airport.common.BaseMapper;
import com.esop.airport.domain.model.TBasMsgPush;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-27 11:30
 **/
public interface MsgPushMapper extends BaseMapper<TBasMsgPush> {

    @Update("UPDATE t_bas_msg_push SET event_type = #{eventType} WHERE = cons_no = #{consNo} ORDER BY id DESC LIMIT 1")
    int updateMsgPushEventType(@Param("consNo") String consNo, @Param("eventType") Integer eventType);

    @Update("UPDATE t_bas_msg_push SET status = #{status}, push_time = now() WHERE = id = #{id}")
    int updateMsgPushStatus(@Param("id") Long id, @Param("status") Long status);
}
