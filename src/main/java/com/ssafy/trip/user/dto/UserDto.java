package com.ssafy.trip.user.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class UserDto {
	private String userId;
    private String userName;
    private String userPassword;
    private String emailId;
    private int roleId;
    private Date createdAt;
    private Date updatedAt;
}
