package com.ssafy.trip.global.config;

import com.ssafy.trip.auth.MyBatisPersistentTokenRepository;
import com.ssafy.trip.auth.jwt.JwtAuthFilter;
import com.ssafy.trip.auth.jwt.JwtUtil;
import com.ssafy.trip.auth.service.CustomerUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration {
    private final CustomerUserDetailService customerUserDetailService;
    private final JwtUtil jwtUtil;
    private MyBatisPersistentTokenRepository persistentTokenRepository;

    private static final String[] AUTH_WHITELIST = {
            "api/auth/**",
            "api/user/join"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CSRF, CORS
        http.csrf((csrf) -> csrf.disable());
        http.cors(Customizer.withDefaults());

        //세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 X
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));

        //FormLogin, BasicHttp 비활성화
        http.formLogin((form) -> form.disable());
        http.httpBasic(AbstractHttpConfigurer::disable);


        //JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(new JwtAuthFilter(jwtUtil, customerUserDetailService), UsernamePasswordAuthenticationFilter.class);


        // 권한 규칙 작성
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().permitAll()//@PreAuthrization을 사용할 것이기 때문에 모든 경로에 대한 인증처리는 Pass
        );

        return http.build();
    }



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
}