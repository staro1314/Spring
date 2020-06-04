package com.example.spring;

import com.example.spring.domain.annotation.MpkScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
@MpkScanner(basePackage = {"com.example.spring.domain.service.serviceImpl"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
