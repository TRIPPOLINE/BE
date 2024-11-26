package com.ssafy.trip.user.controller;

import com.ssafy.trip.auth.dto.JoinDto;
import com.ssafy.trip.auth.dto.TokenDto;
import com.ssafy.trip.auth.dto.request.LoginRequestDto;
import com.ssafy.trip.auth.service.AuthService;
import com.ssafy.trip.user.dto.request.PasswordResetRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.user.dto.request.PasswordUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;
	private final AuthService authService;

	public UserController(UserService userService, AuthService authService) {
		this.userService = userService;
		this.authService = authService;
	}

	@GetMapping("/join")
	public ResponseEntity<?> joinPage() {
		return new ResponseEntity<>("User registration page", HttpStatus.OK);
	}

//	@GetMapping("/{userid}")
//	public ResponseEntity<?> idCheck(@PathVariable("userid") String userId) {
//		try {
//			int cnt = userService.idCheck(userId);
//			return new ResponseEntity<>(cnt, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	// 회원가입
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody JoinDto joinDto) {
		userService.joinUser(joinDto);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// 회원 목록
	@GetMapping("/list")
	public ResponseEntity<?> listNotice(@RequestParam Map<String, String> map) {
		List<UserDto> list = userService.listUser(map);
		if (list == null || list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 회원 조회
	@GetMapping("/select/{user_id}")
	public ResponseEntity<?> getModify(@PathVariable String user_id) {
		UserDto userDto = userService.selectUser(user_id);
		if (userDto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	// 회원 탈퇴
	@PostMapping("/delete/{user_id}")
	public ResponseEntity<?> delete(@PathVariable String user_id) {
		userService.deleteUser(user_id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// 회원 정보 수정
	@PostMapping("/update/{user_id}")
	public ResponseEntity<?> modify(@RequestBody UserDto userDto) {
		userService.updateUser(userDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/password/reset")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest request) {
		try {
			Map<String, String> result = userService.resetPassword(
					request.getUserId(),
					request.getUserName(),
					request.getEmail()
			);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(Map.of("error", e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("password/update")
	public ResponseEntity<?> updatePassword(Authentication authentication, @RequestBody PasswordUpdateRequest request){

		String userId = authentication.getName();
		userService.updatePassword(userId, request.getCurrentPassword(), request.getNewPassword());
		log.info("비밀번호 업데이트 요청 정상적으로 수행됨");

		TokenDto responseToken = authService.login(LoginRequestDto.builder().
				userId(userId).
				password(request.getNewPassword()).
				build());

		return ResponseEntity.ok(responseToken);
	}
}