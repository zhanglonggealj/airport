package com.esop.airport.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * Created by liliqiang on 2018/3/16.
 */
@Configuration
@MapperScan(basePackages = {"com.esop.airport.domain.middle.smapper"} ,sqlSessionTemplateRef = "sqlSessionTemplate2", sqlSessionFactoryRef = "sqlSessionFactory2")
public class MiddleDBConfig {

    @Autowired
    @Qualifier("middleDB")
    private DataSource middleDB;


    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //是否开启驼峰命名
        configuration.setMapUnderscoreToCamelCase(true);
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(middleDB); // 使用titan数据源, 连接titan库
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2()); // 使用上面配置的Factory
        return template;
    }

}
