package com.ssafy.trip.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()  // 모든 요청 허용
            )
            .httpBasic(Customizer.withDefaults())  // HTTP Basic 인증 설정
            .formLogin(form -> form.disable())  // 폼 로그인 비활성화
            .logout(logout -> logout.disable());  // 로그아웃 기능 비활성화

        return http.build();
    }
    
    
    
}