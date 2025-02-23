package com.example.hotel.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@ConditionalOnProperty(name = "mock-db", havingValue = "false")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }
}

