package com.esop.airport.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liliqiang on 2018/3/16.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "airportDB")
    @ConfigurationProperties(prefix = "spring.datasource.airport")
    public DataSource airportDB() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();

        return dataSource;
    }


    @Bean(name = "middleDB")
    @ConfigurationProperties(prefix = "spring.datasource.middle")
    public DataSource middleDB() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();

        return dataSource;
    }

}
