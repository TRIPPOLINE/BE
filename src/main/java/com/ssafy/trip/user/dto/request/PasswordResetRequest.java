package com.ssafy.trip.user.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordResetRequest {
    private String userId;
    private String userName;
    private String email;
}
