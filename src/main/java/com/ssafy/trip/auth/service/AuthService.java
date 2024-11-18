package com.ssafy.trip.auth.service;

import com.ssafy.trip.auth.dto.request.LoginRequestDto;
import com.ssafy.trip.auth.dto.response.LoginResponseDto;

public interface AuthService {
    public LoginResponseDto login(LoginRequestDto request);
}
