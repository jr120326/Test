package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author gxp
 * @date 2022/3/7 18:43
 */
@Configuration
public class SecondDataSourceConfiguration {


    @Bean
    @ConfigurationProperties("camunda.bpm.datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name="camundaBpmDataSource")
    @ConfigurationProperties("camunda.bpm.datasource.postgresql")
    public DataSource secondDataSource() {
        return secondDataSourceProperties().initializeDataSourceBuilder().build();
    }


    @Bean(name="camundaBpmTransactionManager")
    public PlatformTransactionManager secondTransactionManager(@Qualifier("camundaBpmDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
