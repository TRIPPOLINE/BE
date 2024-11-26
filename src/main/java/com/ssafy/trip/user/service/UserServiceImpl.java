package com.ssafy.trip.user.service;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ssafy.trip.auth.dto.JoinDto;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private final UserMapper userMapper;
	private final BCryptPasswordEncoder bCyrptPassowrdEncoder;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
        this.bCyrptPassowrdEncoder = new BCryptPasswordEncoder();
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
				.roleId(2)
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

	@Override
	public Map<String, String> resetPassword(String userId, String userName, String email) {
		UserDto user = userMapper.findUserForPasswordReset(userId, userName, email);
		if (user == null) {
			throw new RuntimeException("일치하는 사용자 정보가 없습니다.");
		}

		// 임시 비번 위한 난수 생성 
		String temporaryPassword = RandomStringUtils.randomAlphanumeric(10);

		// 비번 암호화 및 업데이트
		String encodedPassword = bCyrptPassowrdEncoder.encode(temporaryPassword);
		userMapper.updatePassword(userId, encodedPassword);

		Map<String, String> result = new HashMap<>();
		result.put("temporaryPassword", temporaryPassword);
		result.put("message", "임시 비밀번호가 생성되었습니다. 로그인 후 반드시 비밀번호를 변경해주세요.");

		return result;
	}

	@Override
	@Transactional
	public void updatePassword(String userId, String currentPassword, String newPassword) {
		log.info("현재 비밀번호 : "+currentPassword);
		log.info("변경 비밀번호 : "+newPassword);


		if(checkCurrentPasswordValidation(userId, currentPassword)){
			String encodedNewPassword = bCyrptPassowrdEncoder.encode(newPassword);
			userMapper.updatePassword(userId, encodedNewPassword);
			log.info("비밀 번호가 정상적으로 업데이트됨!!!!!!!!!!!!!!!!!!!!!!!!!!! = "+encodedNewPassword);
			return;
		}
		log.info("사용자 id 또는 pw가 일치하지 않아서 비밀번호 업데이트 불가");
	}

	private boolean checkCurrentPasswordValidation(String userId, String currentPassword){
		//검증하려는 사용자 데이터 객체
		UserDto userDto = userMapper.selectUserById(userId);

		//사용자 현재 비밀번호 일치 검증
		if(matchesPassword(currentPassword, userDto.getPassword())){
			log.info("비번 업데이트 하려는 사용자의 id : "+userId);
			return true;
		}
		
		log.info("비밀 번호 업데이트 하려는 사용자 확인 되지 않음");
		return false;
	}

	private boolean matchesPassword(String originPassword, String encodedPassword){
		return bCyrptPassowrdEncoder.matches(originPassword, encodedPassword);
	}
}
