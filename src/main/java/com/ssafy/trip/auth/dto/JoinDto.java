package com.ssafy.trip.auth.dto;

import lombok.Data;

@Data
public class JoinDto {
    private String id;
    private String name;
    private String password;
    private String email;
}
