package com.esop.airport.config;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * @author arvin liliqiang
 * @create 2019-05-28 08:26
 **/

//@Configuration
//@EnableJpaRepositories(
//        basePackages = "com.esop.airport.domain.repository",
//        entityManagerFactoryRef = "airportEntityManagerFactory",
//        transactionManagerRef = "airportTransactionManager")
//public class AirportDBJpaConfig {
//
//
//    @Qualifier("airportDB")
//    @Autowired
//    DataSource airportDB;
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    @Autowired
//    private HibernateProperties hibernateProperties;
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean airportEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//
////        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
////        jpaVendorAdapter.setGenerateDdl(true);
////        jpaVendorAdapter.setShowSql(true);
//
////        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//
////        factoryBean.setDataSource(airportDB);
////        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
////        factoryBean.setPackagesToScan("com.esop.airport.domain.model");
////        Properties jpaProperties = new Properties();
//////        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
////        jpaProperties.put("spring.jpa.hibernate.naming.physical-strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
////        factoryBean.setJpaProperties(getVendorProperties());
//
//        return builder
//                .dataSource(airportDB)
//                //设置entity所在位置
//                .packages("com.esop.airport.domain.model")
//                .persistenceUnit("airportPersistenceUnit")
//                .properties(getVendorProperties())
//                .build();
//    }
//
//    private Map<String, Object> getVendorProperties() {
//        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
//    }
//
//    @Bean
//    public PlatformTransactionManager airportTransactionManager(@Qualifier("airportDB") DataSource airportDB) {
//        return new DataSourceTransactionManager(airportDB);
//    }
//
//}