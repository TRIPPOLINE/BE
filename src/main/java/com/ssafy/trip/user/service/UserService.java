package com.ssafy.trip.user.service;

import com.ssafy.trip.auth.dto.JoinDto;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.user.dto.UserDto;

public interface UserService {
	
	int idCheck(String userId) ;
	void joinUser(JoinDto join) ;
	void updateUser(UserDto userDto) ;
	
	List<UserDto> listUser(Map<String, String> map) ;
	UserDto selectUser(String user_id) ;
	void deleteUser(String userid) ;
	public Map<String, String> resetPassword(String userId, String userName, String email);
	public void updatePassword(String userId, String currentPassword, String newPassword);
}
