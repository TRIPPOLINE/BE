package com.ssafy.trip.user.dto;

import java.sql.Date;

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
}
