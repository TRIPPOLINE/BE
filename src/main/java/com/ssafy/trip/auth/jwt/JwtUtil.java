package com.ssafy.trip.auth.jwt;

import com.ssafy.trip.auth.dto.RefreshTokenDto;
import com.ssafy.trip.auth.dto.TokenDto;
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
    private final Key accesskey;
    private final Key refreshKey;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;

    public JwtUtil(
            @Value("${jwt.access-secret}") String accessSecretKey,
            @Value("${jwt.refresh-secret}") String refreshSecretKey,
            @Value("${jwt.access-expiration-time}") long accessTokenExpirationTime,
            @Value("${jwt.refresh-expiration-time}") long refreshTokenExpirationTime
    ){
        byte[] accessKeyBytes = Decoders.BASE64.decode(accessSecretKey);
        byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecretKey);
        this.accesskey = Keys.hmacShaKeyFor(accessKeyBytes);
        this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    public TokenDto createAccessToken(UserDto user){
        return createToken(user, accessTokenExpirationTime, refreshTokenExpirationTime);
    }


    private TokenDto createToken(UserDto user, long accessTokenExpirationTime, long refreshTokenExpirationTime){
        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRoleId());

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusSeconds(accessTokenExpirationTime).toInstant()))
                .signWith(accesskey, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusSeconds(refreshTokenExpirationTime).toInstant()))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();

        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).key(user.getId()).build();
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
            Jwts.parserBuilder().setSigningKey(accesskey).build().parseClaimsJws(token);
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

    public String validateRefreshToken(RefreshTokenDto refreshTokenDto){
        String refreshToken = refreshTokenDto.getRefreshToken();

        try{
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(refreshKey).build().parseClaimsJws(refreshToken);

            if(!claims.getBody().getExpiration().before(new Date())){
                String userId = claims.getBody().get("userId").toString();
                String email = claims.getBody().get("email").toString();
                String roleString = (String) claims.getBody().get("role");
                Integer role = Integer.valueOf(roleString);

                return reissueAccessToken(userId, email, role);
            }
        }catch (IllegalArgumentException e){
            return null;
        }

        return null;
    }

    public String reissueAccessToken(String userId, String email, Integer role){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusSeconds(accessTokenExpirationTime).toInstant()))
                .signWith(accesskey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * JWT 클레임 추출
     * 
     * @param accessToken
     * @return
     */
    private Claims parseClaims(String accessToken){
        try {
            return Jwts.parserBuilder().setSigningKey(accesskey).build().parseClaimsJws(accessToken).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
