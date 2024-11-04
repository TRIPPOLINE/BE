package com.ssafy.trip.user.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.user.dto.UserDto;

public interface UserService {
	
	int idCheck(String userId) throws Exception;
	void joinUser(UserDto userDto) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	
	List<UserDto> listUser(Map<String, Object> map) throws Exception;
	UserDto selectUser(String userId) throws Exception;
	void deleteUser(String userid) throws Exception;
}
