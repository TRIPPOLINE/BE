package com.ssafy.trip.auth.controller;

import com.ssafy.trip.auth.dto.TokenDto;
import com.ssafy.trip.auth.dto.request.LoginRequestDto;
import com.ssafy.trip.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> authenticate(
            @RequestBody LoginRequestDto request //TODO : 유효성 검사
    ){
        TokenDto response = this.authService.login(request);
        return ResponseEntity.ok(response);
    }
}
