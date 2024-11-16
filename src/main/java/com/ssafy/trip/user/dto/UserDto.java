package com.ssafy.trip.user.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class UserDto {
	private String id;
    private String name;
    private String password;
    private String email;
    private int role_id;
    private Date created_at;
    private Date updated_at;
}
