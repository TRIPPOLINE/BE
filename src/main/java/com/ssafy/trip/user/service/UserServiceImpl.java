package com.ssafy.trip.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private final UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String userId) {
		return userMapper.idCheck(userId);
	}

	@Override
	public void joinUser(UserDto userDto) {
		userMapper.joinUser(userDto);
	}

	@Override
	public List<UserDto> listUser(Map<String, String> map) {
		return userMapper.listUser(map);
	}

	@Override
	public UserDto selectUser(String userId) {
		return userMapper.selectUser(userId);
	}

	@Override
	public void updateUser(UserDto userDto) {
		userMapper.updateUser(userDto);
	}

	@Override
	public void deleteUser(String userid) {
		userMapper.deleteUser(userid);
	}

}
