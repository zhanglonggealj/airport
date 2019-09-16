package com.esop.airport.domain.service.impl;

import com.esop.airport.domain.service.RedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author arvin liliqiang
 * @create 2018-08-11 下午5:12
 **/
@org.springframework.stereotype.Service
public class RedisCacheImpl implements RedisCacheService {

    private static Logger logger = LoggerFactory.getLogger(RedisCacheImpl.class);

    final static String REDIS_CACHE_KEY = "REDIS_CACHE_KEY";

    final static String REDIS_CACHE_LIST_KEY = "REDIS_CACHE_LIST_KEY";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @Autowired
    Pool<Jedis> pool;

    @Override
    public Object getObj(String key) {

        Object obj = redisTemplate.opsForValue().get(REDIS_CACHE_KEY + key);

        return obj;
    }

    @Override
    public int putObj(String key, Object object, long expire) {

        redisTemplate.opsForValue().set(REDIS_CACHE_KEY + key, object, expire, TimeUnit.SECONDS);//这点4个参数的待解释
        return 1;
    }

    @Override
    public int remove(String key) {

        boolean hasKey = redisTemplate.hasKey(REDIS_CACHE_KEY + key);
        if (hasKey) {
            redisTemplate.delete(REDIS_CACHE_KEY + key);
        }

        return 1;
    }

    @Override
    public <T> T getObjByClass(Class<T> clz, String key) {

        Object obj = redisTemplate.opsForValue().get(REDIS_CACHE_KEY + key);

        if (clz.isInstance(obj)) {
            return clz.cast(obj);
        }

        return null;
    }

    @Override
    public int putObjListPush(String key, Object object) {

        redisTemplate.opsForList().leftPush(REDIS_CACHE_LIST_KEY + key, object);

        return 1;
    }

    @Override
    public Object getObjListPop(String key) {

        Object obj = redisTemplate.opsForList().rightPop(REDIS_CACHE_LIST_KEY + key);

        return obj;
    }

    //    @Override
    public boolean lock(String key, long expireTime, boolean isSpin, long retryTimes) {

        if (expireTime >= 10000) {
            expireTime = 10000;
        }

        if (isSpin) {
            //阻塞锁
            int lockRetryTime = 0;
//            try {
            while (!lock(key, expireTime)) {
                if (lockRetryTime++ > retryTimes) {
                    logger.error("lock exception. key:{}, lockRetryTime:{}", key, lockRetryTime);
                    return false;
                }
            }
            return true;
//            } finally {
//               unlock(key);
//            }
        } else {
            //非阻塞锁
//            try {
            if (!lock(key, expireTime)) {
                logger.error("lock exception. key:{}", key);
                return false;
            }
            return true;
//            } finally {
//                unlock(key);
//            }
        }

    }

    //    @Override
    public boolean unlock(String key) {
        return redisTemplate.delete(key);
    }


    /*****分布式锁临时解决方案 -- 以下内容将要进行抽离*****/

    //key的TTL,一天
    private static final int finalDefaultTTLwithKey = 10;

    private static final boolean Success = true;

    /**
     * 加锁,同时设置锁超时时间
     *
     * @param key        分布式锁的key
     * @param expireTime 单位是ms
     * @return
     */
    private boolean lock(String key, long expireTime) {

        logger.debug("redis lock debug, start. key:[{}], expireTime:[{}]", key, expireTime);
        long now = Instant.now().toEpochMilli();
        long lockExpireTime = now + expireTime;

        //setnx
        boolean executeResult = redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(lockExpireTime));
        logger.debug("redis lock debug, setnx. key:[{}], expireTime:[{}], executeResult:[{}]", key, expireTime, executeResult);

        //取锁成功,为key设置expire
        if (executeResult == Success) {
            redisTemplate.expire(key, finalDefaultTTLwithKey, TimeUnit.SECONDS);
            return true;
        }
        //没有取到锁,继续流程
        else {
            Object valueFromRedis = this.getKeyWithRetry(key, 3);
            // 避免获取锁失败,同时对方释放锁后,造成NPE
            if (valueFromRedis != null) {
                //已存在的锁超时时间
                long oldExpireTime = Long.parseLong((String) valueFromRedis);
                logger.debug("redis lock debug, key already seted. key:[{}], oldExpireTime:[{}]", key, oldExpireTime);
                //锁过期时间小于当前时间,锁已经超时,重新取锁
                if (oldExpireTime <= now) {
                    logger.debug("redis lock debug, lock time expired. key:[{}], oldExpireTime:[{}], now:[{}]", key, oldExpireTime, now);
                    String valueFromRedis2 = (String) redisTemplate.opsForValue().getAndSet(key, String.valueOf(lockExpireTime));
                    long currentExpireTime = Long.parseLong(valueFromRedis2);
                    //判断currentExpireTime与oldExpireTime是否相等
                    if (currentExpireTime == oldExpireTime) {
                        //相等,则取锁成功
                        logger.debug("redis lock debug, getSet. key:[{}], currentExpireTime:[{}], oldExpireTime:[{}], lockExpireTime:[{}]", key, currentExpireTime, oldExpireTime, lockExpireTime);
                        redisTemplate.expire(key, finalDefaultTTLwithKey, TimeUnit.SECONDS);
                        return true;
                    } else {
                        //不相等,取锁失败
                        return false;
                    }
                }
            } else {
                logger.warn("redis lock,lock have been release. key:[{}]", key);
                return false;
            }
        }
        return false;
    }

    private Object getKeyWithRetry(String key, int retryTimes) {
        int failTime = 0;
        while (failTime < retryTimes) {
            try {
                return redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                failTime++;
                if (failTime >= retryTimes) {
                    throw e;
                }
            }
        }
        return null;
    }

    @Override
    public Boolean setnx(String key, String value) {

        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public Boolean expire(String key, long exTime) {
        return redisTemplate.expire(key, exTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public String getSet(String key, String value) {
        return (String) redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public int removeLock(String key) {

        boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            redisTemplate.delete(key);
        }
        return 1;
    }


}
