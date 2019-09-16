package com.esop.airport.domain.service;

/**
 * redis 缓存服务器类
 * @author arvin liliqiang
 * @create 2018-08-11 下午5:03
 **/
public interface RedisCacheService {

    /**
     * 根据key获取缓存的 字符串
     * @param key
     * @return
     */
    Object getObj(String key);

    /**
     * 向缓存写入 对象
     * @param key
     * @param object
     * @param expire 秒
     * @return 返回1 成功
     */
    int putObj(String key, Object object, long expire);

    /**
     * 移除换成对象
     * @param key
     * @return
     */
    int remove(String key);

    /**
     * 根据key获取缓存的 字符串
     * @param key clz
     * @return
     */
    <T> T getObjByClass(Class<T> clz, String key);


    /**
     * redis Lsit 队列
     * @param key
     * @param object
     * @return
     */
    int putObjListPush(String key, Object object);

    /**
     * reids List 队列
     * @param key
     * @return
     */
    Object getObjListPop(String key);


    /**
     * redis分布式锁
     * @param key 锁key
     * @param expireTime 超时时间最大 10
     * @param isSpin 是否阻塞线程
     * @param retryTimes 获取不到锁等待时间
     */
//    boolean lock(String key, long expireTime, boolean isSpin, long retryTimes);

    /**
     * 解锁
     * @param key 解锁key
     * @return
     */
//    boolean unlock(String key);

    /**
     * 分布式锁
     * @param key
     * @param value
     * @return
     */
    Boolean setnx(String key, String value);

    /**
     * 设置key的有效期，单位是毫秒
     * @param key
     * @param exTime
     * @return
     */
    Boolean expire(String key, long exTime);


    String getSet(String key, String value);

    /**
     * 移除锁对象
     * @param key
     * @return
     */
    int removeLock(String key);
}
