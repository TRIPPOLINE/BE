package com.ssafy.trip.auth.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RefreshTokenDto {
    private Long id;
    private String refreshToken;
    private String keyUserId;
}
