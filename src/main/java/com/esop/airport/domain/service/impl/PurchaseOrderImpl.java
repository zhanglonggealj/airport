package com.esop.airport.domain.service.impl;

import com.esop.airport.common.ConstDef;
import com.esop.airport.domain.mapper.PurchaseOrderMapper;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.PurchaseOrderService;
import com.esop.airport.utils.StringUtils;
import com.esop.airport.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-11 13:47
 **/
@Service
public class PurchaseOrderImpl implements PurchaseOrderService {


    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public List<TPurchaseOrder> findOrdersByConsNoAndCreateTime(String consNo, Long id, String startTime, String endTime) {

        Example example = new Example(TPurchaseOrder.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("type", 2)
                .andEqualTo("status", 1)
                .andEqualTo("consNo", consNo)
                .andLessThan("id", id);

        if (StringUtils.isNotBlank(startTime, endTime)) {

            try {
                endTime = TimeUtils.dateAdd(3, endTime, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            criteria.andBetween("createTime", startTime, endTime);
        }

        example.setOrderByClause("create_time DESC,id DESC");

        PageHelper.startPage(1, ConstDef.PAGE_SIZE, false);

        List<TPurchaseOrder> list = purchaseOrderMapper.selectByExample(example);

        return list;
    }

    @Override
    public List<TPurchaseOrder> findOrdersByUserId(Long userId, Long id, String startTime, String endTime)  {
        Example example = new Example(TPurchaseOrder.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 2)
                .andEqualTo("status", 1)
                .andEqualTo("userId", userId)
                .andLessThan("id", id);

        if (StringUtils.isNotBlank(startTime, endTime)) {
            try {
                endTime = TimeUtils.dateAdd(3, endTime, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            criteria.andBetween("createTime", startTime, endTime);
        }

        example.setOrderByClause("create_time DESC,id DESC");
        PageHelper.startPage(1, ConstDef.PAGE_SIZE, false);

        List<TPurchaseOrder> list = purchaseOrderMapper.selectByExample(example);

        return list;
    }

    @Override
    public int saveOrder(TPurchaseOrder order) {
        return purchaseOrderMapper.insertSelective(order);
    }

    @Override
    public TPurchaseOrder findOredrByOrderId(String orderId) {

        TPurchaseOrder find = new TPurchaseOrder();

        find.setOrderId(orderId);

        find.setStatus(1L);

        return purchaseOrderMapper.selectOne(find);
    }

    @Override
    public List<TPurchaseOrder> findOrdersByUserIdDistinct(Long userId) {
        return purchaseOrderMapper.findOrdersByUserIdDistinct(userId);
    }

    @Override
    public int updateType2Status(Long id, Long type2, Integer issuedType, String sendTime) {
        return purchaseOrderMapper.updateType2Status(id, type2, issuedType, sendTime);
    }

    @Override
    public List<TPurchaseOrder> findOrdersByInOrderId(List<String> orderIds) {
        Example example = new Example(TPurchaseOrder.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("orderId", orderIds);

        List<TPurchaseOrder> list = purchaseOrderMapper.selectByExample(example);

        return list;
    }

    @Override
    public long findMonthMoney(String startDate, String endDate) {

        return purchaseOrderMapper.findMonthMoney(startDate, endDate);
    }

    @Override
    public List<TPurchaseOrder> dayPurchaseOrederList(String startDate, String endDate) {
        return purchaseOrderMapper.findDayPurchaseOrederList(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> findYearData(String startDate, String endDate) {
        return purchaseOrderMapper.findYearData(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> findMonthData(String startDate, String endDate) {
        return purchaseOrderMapper.findMonthData(startDate, endDate);
    }

    @Override
    public int updateType(String orderId, String transactionId) {

        return purchaseOrderMapper.updateType(orderId, transactionId);
    }

    @Override
    public int updateType2AndSendMoney(String orderId, double sendMoney) {
        return purchaseOrderMapper.updateType2AndSendMoney(orderId, sendMoney);
    }

    @Override
    public List<TPurchaseOrder> findBuyOrders() {
        TPurchaseOrder find = new TPurchaseOrder();
        find.setStatus(1L);
        find.setType(2L);
        find.setType2(1L);
        PageHelper.startPage(1, 2000, false);
        return purchaseOrderMapper.select(find);
    }
}
