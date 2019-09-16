package com.esop.airport.domain.mapper;

import com.esop.airport.common.BaseMapper;
import com.esop.airport.domain.model.TPurchaseOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-11 13:48
 **/
public interface PurchaseOrderMapper extends BaseMapper<TPurchaseOrder> {

    @Select("SELECT distinct(cons_no),notify_time FROM t_purchase_order  WHERE  user_id = #{userId} AND type = 2 AND status = 1 ORDER BY notify_time DESC LIMIT 20 ")
    List<TPurchaseOrder> findOrdersByUserIdDistinct(Long userId);

    @Update("UPDATE t_purchase_order SET type2 = #{type2}, issued_type = #{issuedType},send_time = '${sendTime}' WHERE id = #{id}")
    int updateType2Status(@Param("id") Long id, @Param("type2") Long type2, @Param("issuedType") Integer issuedType, @Param("sendTime") String sendTime);

    @Select("SELECT SUM(total_fee) FROM t_purchase_order WHERE notify_time BETWEEN #{startDate} AND #{endDate} AND type = 2")
    long findMonthMoney(@Param("startDate") String startDate,@Param("endDate") String endDate);

    @Select("SELECT * FROM t_purchase_order WHERE notify_time BETWEEN #{startDate} AND #{endDate} AND type = 2")
    List<TPurchaseOrder> findDayPurchaseOrederList(@Param("startDate") String startDate,@Param("endDate") String endDate);

    @Select("SELECT month,COUNT(1) AS count,SUM(total_fee) AS total FROM (SELECT DATE_FORMAT(notify_time, '%m') month,total_fee total_fee FROM t_purchase_order " +
            " WHERE notify_time BETWEEN #{startDate} AND #{endDate} AND type = 2 ) a GROUP BY month")
    List<Map<String, Object>> findYearData(@Param("startDate") String startDate,@Param("endDate") String endDate);

    @Select("SELECT day, COUNT(1) AS count, SUM(total_fee) AS total FROM (SELECT DATE_FORMAT(notify_time, '%d') day, total_fee total_fee FROM t_purchase_order " +
            " WHERE notify_time BETWEEN #{startDate} AND #{endDate} AND type = 2 ) a GROUP BY day")
    List<Map<String, Object>> findMonthData(@Param("startDate") String startDate,@Param("endDate") String endDate);

    @Update("UPDATE t_purchase_order SET type = 2, notify_time = NOW(), transaction_id = #{transactionId} WHERE order_id = #{orderId}")
    int updateType(@Param("orderId") String orderId, @Param("transactionId") String transactionId);

    @Update("UPDATE t_purchase_order SET issued_time = NOW(), type2 = 2, send_money = #{sendMoney} WHERE order_id = #{orderId} AND type2 = 1")
    int updateType2AndSendMoney(@Param("orderId") String orderId, @Param("sendMoney") double sendMoney);
}


