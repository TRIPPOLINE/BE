package com.ssafy.trip.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(auth -> auth.disable()) // formLogin 비활성화
                .httpBasic(auth -> auth.disable()) // httpBasic 비활성화
                .csrf(auth -> auth.disable());     // CSRF 비활성화

        http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/login", "/", "/join").permitAll() // 로그인, 메인, 회원가입은 모두 허용
                        .requestMatchers("/admin").hasRole("ADMIN")          // /admin 경로는 ADMIN 권한 필요
                        .anyRequest().authenticated()                       // 나머지는 인증 필요
                );

        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 무상태 세션 설정
                );

        return http.build();
    }
}