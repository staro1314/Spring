package com.example.spring.domain.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: Staro
 * @date: 2019/3/1317:57
 * @Description:
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Configration {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.spring.domain.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("SpringBoot Redis Mybatis Druid Demo")
                .description("swagger2")
                .version("1.0")
                .build();
    }
}
