package com.ssafy.trip.user.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ssafy.trip.auth.dto.JoinDto;
import java.sql.Date;
import java.time.LocalDateTime;
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
	private final BCryptPasswordEncoder bCyrptPassowrdEncoder;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
        this.bCyrptPassowrdEncoder = new BCryptPasswordEncoder();;
    }

	@Override
	public int idCheck(String userId) {
		return userMapper.idCheck(userId);
	}

	@Override
	public void joinUser(JoinDto joinDto) {
		UserDto userDto = UserDto.builder()
				.id(joinDto.getId())
				.name(joinDto.getName())
				.password(bCyrptPassowrdEncoder.encode(joinDto.getPassword()))
				.email(joinDto.getEmail())
				.roleId(1)
				.created_at(LocalDateTime.now())
				.updated_at(LocalDateTime.now())
				.build();

		if(userMapper.selectUser(joinDto.getId())!=null){
			return; //todo : 예외 처리
		}
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
