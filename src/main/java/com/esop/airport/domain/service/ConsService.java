package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TBasCons;

import java.util.List;

/**
 * @author arvin liliqiang
 * @create 2019-05-28 08:44
 **/
public interface ConsService {

    /**
     * 根据预留手机号查找对应商户
     * @param phone
     * @return
     */
    List<TBasCons> findConsListByPhone(String phone);

    /**
     * 根据商户号 查询商户
     * @param consNo
     * @return
     */
    TBasCons findConsByconsNo(String consNo);

    /**
     * 新增商户信息
     * @param cons
     * @return
     */
    int saveCons(TBasCons cons);

    /**
     * 根据主键更新商户
     * @param cons
     * @return
     */
    int updateCons(TBasCons cons);

    /**
     * 根据商户号获取
     * @param consNos
     * @return
     */
    List<TBasCons> findConsByConsNos(Iterable<String> consNos);


    /**
     * 根据商户号 && 商户名称 模糊查询
     * @param consNo
     * @param consName
     * @return
     */
    List<TBasCons> findCOnsByLikeConsNoConsName(String consNo, String consName);

    /**
     * 获取所有商户信息
     * @return
     */
    long findConsConut();
}
