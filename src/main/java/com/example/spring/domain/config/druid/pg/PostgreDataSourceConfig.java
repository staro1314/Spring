package com.example.spring.domain.config.druid.pg;

import com.example.spring.domain.config.druid.DruidConnectPoolConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author: Staro
 * @date: 2019/4/1914:33
 * @Description:
 */
@Slf4j
@Configuration
@MapperScan(basePackages = PostgreDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "pgSqlSessionTemplate")
public class PostgreDataSourceConfig {
    static final String PACKAGE = "com.example.spring.domain.dao.pg";
    private static final String MAPPER_LOCATION = "classpath:mapper/pg/**/*.xml";

    private final DruidConnectPoolConfigBean config;
    @Value("${pg.url}")
    private String url;

    @Value("${pg.driver-class-name}")
    private String driverName;

    @Value("${pg.username}")
    private String username;

    @Value("${pg.password}")
    private String password;

    @Autowired
    public PostgreDataSourceConfig(DruidConnectPoolConfigBean config) {
        this.config = config;
    }

    @Bean(name = "pgDataSource")
    public DataSource initPgDataSource() {
        return config.initDataSource(url, driverName, username, password);
    }

    @Bean(name = "pgSqlSessionFactory")
    public SqlSessionFactory getPgSqlSessionFactory(@Qualifier("pgDataSource") DataSource pgDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(pgDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PostgreDataSourceConfig.MAPPER_LOCATION));
        return factoryBean.getObject();
    }

    @Bean(name = "pgSqlSessionTemplate")
    public SqlSessionTemplate getPgSqlSessionTemplate(@Qualifier("pgSqlSessionFactory") SqlSessionFactory pgSqlSessionFactory) {
        return new SqlSessionTemplate(pgSqlSessionFactory);
    }

    @Bean(name = "pgTransactionManager")
    public DataSourceTransactionManager transactionManagerPg(@Qualifier("pgDataSource") DataSource pgDataSource) {
        return new DataSourceTransactionManager(pgDataSource);
    }
}
