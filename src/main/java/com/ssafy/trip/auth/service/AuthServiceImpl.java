package com.ssafy.trip.auth.service;

import com.ssafy.trip.auth.dto.request.LoginRequestDto;
import com.ssafy.trip.auth.dto.response.LoginResponseDto;
import com.ssafy.trip.auth.jwt.JwtUtil;
import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        String userId = request.getUserId();
        String password = request.getPassword();
        UserDto userDto = userMapper.selectUser(userId);

        if(userDto == null){
            throw new UsernameNotFoundException("유저가 존재하지 않습니다");
        }

        if(!encoder.matches(password, userDto.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }

        String accessToken = jwtUtil.createAccessToken(userDto);

        return new LoginResponseDto(accessToken);
    }
}
