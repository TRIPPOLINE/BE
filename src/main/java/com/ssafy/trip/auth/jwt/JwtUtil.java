package com.ssafy.trip.auth.jwt;

import com.ssafy.trip.user.dto.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private final Key key;
    private final long accessTokenExpirationTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration-time}") long accessTokenExpirationTime
    ){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpirationTime = accessTokenExpirationTime;
    }

    public String createAccessToken(UserDto user){
        return createToken(user, accessTokenExpirationTime);
    }

    /**
     * Accees Token 생성
     * 
     * @param user
     * @param expirationTime
     * @return
     */
    private String createToken(UserDto user, long expirationTime){
        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRoleId());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 토큰에서 유저의 아이디 추출
     * 
     * @param token
     * @return
     */
    public String getUserId(String token){
        return parseClaims(token).get("userId", String.class);
    }

    /**
     * JWT 토큰 검증
     * 
     * @param token
     * @return
     */
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("유효하지 않은 토큰", e);
        } catch (ExpiredJwtException e) {
            log.info("토큰 만료", e);
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 토큰", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT 클레임이 없음", e);
        }
        return false;
    }

    /**
     * JWT 클레임 추출
     * 
     * @param accessToken
     * @return
     */
    private Claims parseClaims(String accessToken){
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
