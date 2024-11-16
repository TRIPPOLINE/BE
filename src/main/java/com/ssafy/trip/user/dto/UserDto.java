package com.ssafy.trip.user.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
	private String id;
    private String name;
    private String password;
    private String email;
    private int roleId;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
