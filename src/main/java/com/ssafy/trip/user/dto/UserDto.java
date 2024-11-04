package com.ssafy.trip.user.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class UserDto {
	private String user_id;
    private String user_name;
    private String user_password;
    private String email_id;
    private String role;
    private Date created_at;
    private Date updated_at;
}
