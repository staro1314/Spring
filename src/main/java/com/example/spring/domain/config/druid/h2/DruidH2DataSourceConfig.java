package com.example.spring.domain.config.druid.h2;

import com.example.spring.domain.config.druid.DruidConnectPoolConfigBean;
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
 * @date: 2019/4/1711:42
 * @Description:
 */
@Configuration
@MapperScan(basePackages = DruidH2DataSourceConfig.PACKAGE, sqlSessionTemplateRef = "h2SqlSessionTemplate")
public class DruidH2DataSourceConfig {

    static final String PACKAGE = "com.example.spring.domain.dao.h2";

    private static final String MAPPER_LOCATION = "classpath:mapper/h2/**/*.xml";

    @Value("${h2.url}")
    private String url;
    @Value("${h2.driver-class-name}")
    private String driverName;
    @Value("${h2.username}")
    private String username;
    @Value("${h2.password}")
    private String password;

    @Autowired
    private final DruidConnectPoolConfigBean config;

    public DruidH2DataSourceConfig(DruidConnectPoolConfigBean config) {
        this.config = config;
    }

    @Bean(name = "h2DataSource")
    public DataSource initH2DataSource() {
        return config.initDataSource(url, driverName, username, password);
    }

    @Bean(name = "h2SqlSessionFactory")
    public SqlSessionFactory getH2SqlSessionFactory(@Qualifier("h2DataSource") DataSource h2DataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(h2DataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DruidH2DataSourceConfig.MAPPER_LOCATION));
        return factoryBean.getObject();
    }

    @Bean(name = "h2SqlSessionTemplate")
    public SqlSessionTemplate getH2SqlSessionTemplate(@Qualifier("h2SqlSessionFactory")SqlSessionFactory h2SqlSessionFactory) {
        return new SqlSessionTemplate(h2SqlSessionFactory);
    }

    @Bean(name = "h2TransactionManager")
    public DataSourceTransactionManager transactionManagerH2(@Qualifier("h2DataSource") DataSource h2DataSource) {
        return new DataSourceTransactionManager(h2DataSource);
    }
}
