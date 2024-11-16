package com.ssafy.trip.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.user.dto.UserDto;

@Mapper
public interface UserMapper {
	int idCheck(String userId) ;
	void joinUser(UserDto userDto) ;
	void updateUser(UserDto userDto) ;
	List<UserDto> listUser(Map<String, String> map) ;
	UserDto selectUser(String userId) ;
	void deleteUser(String userid);
}
