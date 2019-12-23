package com.example.spring.domain.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: Staro
 * @date: 2019/3/1315:03
 * @Description:
 */
@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "default.druid")
public class DruidConnectPoolConfigBean {

//    @Value("${default.druid.initialSize}")
    private int initialSize;

//    @Value("${default.druid.minIdle}")
    private int minIdle;

//    @Value("${default.druid.maxActive}")
    private int maxActive;

//    @Value("${default.druid.maxWait}")
    private int maxWait;

//    @Value("${default.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

//    @Value("${default.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

//    @Value("${default.druid.validationQuery}")
    private String validationQuery;

//    @Value("${default.druid.testWhileIdle}")
    private boolean testWhileIdle;

//    @Value("${default.druid.testOnBorrow}")
    private boolean testOnBorrow;

//    @Value("${default.druid.testOnReturn}")
    private boolean testOnReturn;

//    @Value("${default.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;

//    @Value("${default.druid.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;

//    @Value("${default.druid.filters}")
    private String filters;

//    @Value("${default.druid.connectionProperties}")
    private String connectionProperties;

//    @Value("${druid.datasource.type}")
//    private String dbType;

    public DataSource initDataSource(String url, String driveClass, String username, String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driveClass);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setTimeBetweenConnectErrorMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        try {
            druidDataSource.setFilters(filters);
        } catch (SQLException e) {
            log.info("druidDataSource set filters fail");
        }
        druidDataSource.setConnectionProperties(connectionProperties);
        return druidDataSource;
    }
}
