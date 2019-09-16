package com.esop.airport.domain.service;

import com.esop.airport.domain.model.TToken;

import java.util.List;

/**
 * @author arvin liliqiang
 * @create 2019-05-28 08:44
 **/
public interface TokenService {

    /**
     * 保存Token
     * @param token
     * @return
     */
    int saveToken(TToken token);


    /**
     * 根据token bindID && token类型查找所有有效的token
     * @param bindId
     * @param category
     * @return
     */
    List<TToken> findTokensByUserIdAndCategory(Long bindId, Integer category);

    /**
     * 删除作废token
     * @param userId
     * @param category
     * @return
     */
    int delToken(Long userId, Integer category);

    /**
     * 根据token 清空 缓存方法
     * @param token
     */
    void clearCacheByToken(String token);

    /**
     * 查找token
     * @param tokenCode
     * @return
     */
    TToken findTokenByCode(String tokenCode);
}
