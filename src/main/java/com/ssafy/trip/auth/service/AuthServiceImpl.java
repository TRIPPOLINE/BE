package com.ssafy.trip.auth.service;

import com.ssafy.trip.auth.dto.RefreshTokenDto;
import com.ssafy.trip.auth.dto.TokenDto;
import com.ssafy.trip.auth.dto.request.LoginRequestDto;
import com.ssafy.trip.auth.dto.response.LoginResponseDto;
import com.ssafy.trip.auth.jwt.JwtUtil;
import com.ssafy.trip.auth.mapper.RefreshMapper;
import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final RefreshMapper refreshMapper;

    @Transactional
    @Override
    public TokenDto login(LoginRequestDto request) {
        String userId = request.getUserId();
        String password = request.getPassword();
        UserDto userDto = userMapper.selectUser(userId);

        //유저 검증 및 토큰 생성
        validateUser(password, userDto);
        TokenDto tokenDto = jwtUtil.createAccessToken(userDto);
        
        //리프레시 토큰 로테이션
        rotateRefreshToken(tokenDto);

        return tokenDto;
    }

    private void validateUser(String password, UserDto userDto){
        if(userDto == null){
            throw new UsernameNotFoundException("유저가 존재하지 않습니다");
        }

        if(!encoder.matches(password, userDto.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }
    }

    private void rotateRefreshToken(TokenDto tokenDto){
        RefreshTokenDto refreshTokenDto = RefreshTokenDto.builder().keyUserId(tokenDto.getKey()).refreshToken(tokenDto.getRefreshToken()).build();
        String loginUserId = refreshTokenDto.getKeyUserId();

        if(refreshMapper.existsByKeyUserId(loginUserId)){
            log.info("기존에 DB에 저장된 리프레시 토큰을 삭제");
            refreshMapper.deleteByKeyUserId(loginUserId);
        }

        refreshMapper.save(refreshTokenDto);
    }

    public String validateRefreshToken(String refreshToken){
        RefreshTokenDto refreshTokenDto = getRefreshToken(refreshToken).get();
        String reissuedAccessToken = jwtUtil.validateRefreshToken(refreshTokenDto);

        return reissuedAccessToken;
    }

    private Optional<RefreshTokenDto> getRefreshToken(String refreshToken){
        return refreshMapper.selectRefreshToken(refreshToken);
    }

    //TODO : 리팩터링
    public Map<String, String> createTokenResponse(String accessToken){
        Map<String, String> map = new HashMap<>();
        if(accessToken==null){
            map.put("error", "Forbidden");
            map.put("status", "403");
            map.put("message", "리프레시 토큰이 만료됨, 재로그인 필요");

            return map;
        }

        map.put("status", "200");
        map.put("message", "리프레시 토큰으로 엑세스 토큰 재발급 완료");
        map.put("accessToken", accessToken);

        return map;
    }
}
