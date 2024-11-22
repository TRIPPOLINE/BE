package com.ssafy.trip.auth.controller;

import com.ssafy.trip.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/reissue")
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_USER')")
public class RefreshController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> validateRefreshToken(@RequestBody HashMap<String, String> body){
        String reissuedAccessToken = authService.validateRefreshToken(body.get("refreshToken"));
        Map<String, String> tokenResponse = authService.createTokenResponse(reissuedAccessToken);

        if(tokenResponse.get("status").equals("402")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenResponse);
        }

        return ResponseEntity.ok(tokenResponse);
    }
}
