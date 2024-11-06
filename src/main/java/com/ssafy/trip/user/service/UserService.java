package com.ssafy.trip.user.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.user.dto.UserDto;

public interface UserService {
	
	int idCheck(String userId) ;
	void joinUser(UserDto userDto) ;
	void updateUser(UserDto userDto) ;
	
	List<UserDto> listUser(Map<String, String> map) ;
	UserDto selectUser(String user_id) ;
	void deleteUser(String userid) ;
}
