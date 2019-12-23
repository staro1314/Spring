package com.example.spring.domain.config.druid.mysql;


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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author: Staro
 * @date: 2019/3/1315:31
 * @Description:
 */
@Slf4j
@Configuration
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplateMysql")
public class MysqlDataSourceConfig {
    //dao层扫描
    static final String PACKAGE = "com.example.spring.domain.dao.mysql";
    //mapper路径
    // '?'   匹配任何单个字符
    // '*'   匹配 0~n 个字符
    // '**'  匹配 0~n 级目录
    private static final String MAPPER_LOCATION = "classpath:mapper/mysql/**/*.xml";

    private DruidConnectPoolConfigBean config;

    @Value("${druid.datasource.url}")
    private String url;

    @Value("${druid.datasource.driverClassname}")
    private String driverClassname;

    @Value("${druid.datasource.username}")
    private String username;

    @Value("${druid.datasource.password}")
    private String password;

    @Autowired
    public MysqlDataSourceConfig(DruidConnectPoolConfigBean config) {
        this.config = config;
    }


    @Bean(name = "dataSourceMysql")
    @Primary
    public DataSource initMysqlDataSource() {
        return config.initDataSource(url, driverClassname, username, password);
    }

    @Bean(name = "sqlSessionFactoryMysql")
    @Primary
    public SqlSessionFactory getMysqlSqlSessionFactory(@Qualifier("dataSourceMysql")DataSource dataSourceMysql) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceMysql);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MysqlDataSourceConfig.MAPPER_LOCATION));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplateMysql")
    @Primary
    public SqlSessionTemplate getMysqlSqlSessionTemplate(@Qualifier("sqlSessionFactoryMysql")SqlSessionFactory sqlSessionFactoryMysql) {
        return new SqlSessionTemplate(sqlSessionFactoryMysql);
    }

    @Bean(name = "mysqlTransactionManagerMysql")
    @Primary
    public DataSourceTransactionManager transactionManagerMysql(@Qualifier("dataSourceMysql")DataSource dataSourceMysql) {
        return new DataSourceTransactionManager(dataSourceMysql);
    }
}
