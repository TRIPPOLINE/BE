package com.ssafy.trip.auth.controller;

import com.ssafy.trip.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class RefreshController {
    private AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<?> validateRefreshToken(@RequestBody HashMap<String, String> body){
        String reissuedAccessToken = authService.validateRefreshToken(body.get("refreshToken"));
        Map<String, String> tokenResponse = authService.createTokenResponse(reissuedAccessToken);

        if(tokenResponse.get("status").equals("402")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenResponse);
        }

        return ResponseEntity.ok(tokenResponse);
    }
}
