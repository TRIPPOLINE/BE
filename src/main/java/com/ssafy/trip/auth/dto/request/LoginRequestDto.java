package com.ssafy.trip.auth.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequestDto {
    private String userId;
    private String password;
}
