package com.ssafy.trip.user.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordUpdateRequest {
    private String currentPassword;
    private String newPassword;
}
