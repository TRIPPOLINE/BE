package com.ssafy.trip.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired

	private final UserMapper userMapper;

	
	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
	}

	@Override
	public void joinUser(UserDto userDto) throws Exception {
		userMapper.joinUser(userDto);
	}

	@Override
	public List<UserDto> listUser(Map<String, Object> map) throws Exception {
		return userMapper.listUser(map);
	}

	@Override
	public UserDto selectUser(String userId) throws Exception {
		return userMapper.selectUser(userId);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		userMapper.updateUser(userDto);
	}

	@Override
	public void deleteUser(String userid) throws Exception {
		userMapper.deleteUser(userid);
	}

}
