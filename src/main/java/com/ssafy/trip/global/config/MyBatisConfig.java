package com.ssafy.trip.global.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.ssafy.trip.auth.mapper") // Mapper 인터페이스가 위치한 패키지
class MyBatisConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/your_database")
                .username("your_username")
                .password("your_password")
                .build();
    }
}