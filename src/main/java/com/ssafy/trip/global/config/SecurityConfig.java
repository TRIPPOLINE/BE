package com.ssafy.trip.global.config;

import com.ssafy.trip.auth.jwt.LoginFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
//                .authorizeHttpRequests(authz -> authz
//                        .anyRequest().permitAll()  // 모든 요청 허용
//                )
//                .httpBasic(Customizer.withDefaults())  // HTTP Basic 인증 설정
//                .formLogin(form -> form.disable())  // 폼 로그인 비활성화
//                .logout(logout -> logout.disable());  // 로그아웃 기능 비활성화
//
//        return http.build();
//    }
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


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

        http
                .addFilter(new LoginFilter(authenticationManager(authenticationConfiguration))); //로그인 필터

        return http.build();
    }
}