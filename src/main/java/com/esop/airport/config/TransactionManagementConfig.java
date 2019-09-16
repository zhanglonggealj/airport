package com.esop.airport.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by liliqiang on 2018/3/16.
 */

@EnableTransactionManagement
@Configuration
public class TransactionManagementConfig {

    @Bean
    public PlatformTransactionManager airportTransactionManager(@Qualifier("airportDB") DataSource airportDB) {
        return new DataSourceTransactionManager(airportDB);
    }

    @Bean
    public PlatformTransactionManager middleTransactionManager(@Qualifier("middleDB") DataSource airportDB) {
        return new DataSourceTransactionManager(airportDB);
    }

}
