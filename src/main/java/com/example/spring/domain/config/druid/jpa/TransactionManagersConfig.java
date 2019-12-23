package com.example.spring.domain.config.druid.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author: Staro
 * @date: 2019/7/515:08
 * @Description:
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagersConfig {
    @Autowired
    EntityManagerFactory emf;
    @Autowired
    private DataSource dataSource;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm =
                new JpaTransactionManager();
        tm.setEntityManagerFactory(emf);
        tm.setDataSource(dataSource);
        return tm;
    }
}
