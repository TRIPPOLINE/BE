package com.ssafy.trip.auth.jwt;

import com.ssafy.trip.auth.service.CustomerUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomerUserDetailService customerUserDetailService;

    public JwtAuthFilter(JwtUtil jwtUtil, CustomerUserDetailService customerUserDetailService) {
        this.jwtUtil = jwtUtil;
        this.customerUserDetailService = customerUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        log.info("Authorization : " + authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                if (jwtUtil.validateToken(token)) {
                    String userId = jwtUtil.getUserId(token);

                    UserDetails userDetails = customerUserDetailService.loadUserByUsername(userId);
                    log.info("userDetails : " + userDetails.toString());

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    log.info("usernamePasswordAuthenticationToken : " + usernamePasswordAuthenticationToken.toString());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (ExpiredJwtException e) {
                log.info("토큰 만료", e);
                handleExpiredToken(response);
                return;
            } catch (Exception e) {
                log.error("토큰 검증 실패", e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void handleExpiredToken(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Token has expired\", \"reissueUrl\": \"http://localhost:8080/api/reissue\"}");
    }
}
