package com.ssafy.trip.auth.service;

import com.ssafy.trip.auth.dto.TokenDto;
import com.ssafy.trip.auth.dto.request.LoginRequestDto;
import com.ssafy.trip.auth.dto.response.LoginResponseDto;

import java.util.Map;

public interface AuthService {
    public TokenDto login(LoginRequestDto request);
    public String validateRefreshToken(String refreshToken);
    public Map<String, String> createTokenResponse(String accessToken);
}
