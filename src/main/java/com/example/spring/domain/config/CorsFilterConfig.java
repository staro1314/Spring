package com.example.spring.domain.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author: Staro
 * @date: 2019/3/1316:03
 * @Description:
 */
@Configuration
public class CorsFilterConfig {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.addAllowedOrigin("*"); //允许任意域名进行请求
        corsConfiguration.addAllowedHeader("*"); //允许任意请求头
        corsConfiguration.addAllowedMethod("*"); //允许任意请求方式
        corsConfiguration.setAllowCredentials(true); //允许在请求中包含cookie
        return corsConfiguration;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new org.springframework.web.filter.CorsFilter(source));
        // 多个过滤器需要设置优先级，registration.setOrder(Integer.MAX_VALUE - 1)，提高执行的优先级 Integer.MAX_VALUE - 1
        bean.setOrder(Integer.MAX_VALUE - 1);
        return bean;
    }

}
