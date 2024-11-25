package com.ssafy.trip.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.user.dto.UserDto;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	int idCheck(String userId) ;
	void joinUser(UserDto userDto) ;
	void updateUser(UserDto userDto) ;
	List<UserDto> listUser(Map<String, String> map) ;
	UserDto selectUser(String userId) ;
	void deleteUser(String userid);
	UserDto findUserForPasswordReset(
			@Param("userId") String userId,
			@Param("userName") String userName,
			@Param("email") String email
	);
	void updatePassword(
			@Param("userId") String userId,
			@Param("password") String password
	);
}
