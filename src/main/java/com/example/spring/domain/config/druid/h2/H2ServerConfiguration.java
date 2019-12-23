package com.example.spring.domain.config.druid.h2;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;


@Configuration
@Profile({"dev"})
public class H2ServerConfiguration {

    @Bean
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer().start();
    }


}
