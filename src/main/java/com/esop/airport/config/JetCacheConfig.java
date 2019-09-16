package com.esop.airport.config;

import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import com.alicp.jetcache.embedded.LinkedHashMapCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.JavaValueDecoder;
import com.alicp.jetcache.support.JavaValueEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author arvin liliqiang
 * @create 2018-08-14 下午6:31
 **/
@Configuration
@EnableMethodCache(basePackages = "com.jkhh.provider.dubbo.impl")
@EnableCreateCacheAnnotation
public class JetCacheConfig {

    @Autowired
    private RedisProperties properties;

    /**
     * 注入 RedisConnectionFactory
     */
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置redisTemplate
     * <p>
     * 通过redisConnectionFactory引入redis在配置文件中的连接配置
     */

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public Pool<Jedis> pool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(properties.getJedis().getPool().getMaxIdle());
        config.setMaxTotal(properties.getJedis().getPool().getMaxActive());
        config.setMaxWaitMillis(properties.getJedis().getPool().getMaxWait().toMillis());
        JedisPool pool = new JedisPool(config, properties.getHost(), properties.getPort(), 100
                , properties.getPassword(), properties.getDatabase() );
        return pool;

    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider();
    }

    @Bean
    public GlobalCacheConfig config(SpringConfigProvider configProvider, Pool<Jedis> pool) {//其实spring 自带框架可以
        Map localBuilders = new HashMap();
        EmbeddedCacheBuilder localBuilder = LinkedHashMapCacheBuilder
                .createLinkedHashMapCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE);
        localBuilders.put(CacheConsts.DEFAULT_AREA, localBuilder);

        Map remoteBuilders = new HashMap();
        RedisCacheBuilder remoteCacheBuilder = RedisCacheBuilder.createRedisCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .valueEncoder(JavaValueEncoder.INSTANCE)
                .valueDecoder(JavaValueDecoder.INSTANCE)
                .jedisPool(pool);
        remoteBuilders.put(CacheConsts.DEFAULT_AREA, remoteCacheBuilder);

        GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
        globalCacheConfig.setConfigProvider(configProvider);
        globalCacheConfig.setLocalCacheBuilders(localBuilders);
        globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
        globalCacheConfig.setStatIntervalMinutes(60);
        globalCacheConfig.setAreaInCacheName(false);


        return globalCacheConfig;
    }
}
