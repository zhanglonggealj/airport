package com.esop.airport.domain.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import com.esop.airport.domain.mapper.TokenMapper;
import com.esop.airport.domain.model.TToken;
import com.esop.airport.domain.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author arvin liliqiang
 * @create 2019-05-28 08:44
 **/
@Service
public class TokenImpl implements TokenService {

    @Autowired
    TokenMapper tokenMapper;

    @Override
    public int saveToken(TToken token) {
        return tokenMapper.insertSelective(token);
    }

    @Override
    public List<TToken> findTokensByUserIdAndCategory(Long bindId, Integer category) {
        return tokenMapper.findTokensByUserIdAndCategory(bindId, category);
    }

    @Override
    public int delToken(Long userId, Integer category) {
        return tokenMapper.delToken(userId, category);
    }

    @Override
    @CacheInvalidate(name="findTokenByCode")
    public void clearCacheByToken(String token) {

    }

    @Cached(name="findTokenByCode", expire = 60 * 3)
    @Override
    public TToken findTokenByCode(String tokenCode) {
        return tokenMapper.findTokenByCode(tokenCode);
    }
}
