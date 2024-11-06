package com.ssafy.trip.user.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.service.UserService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminUserController {
//	private final UserService userSerivce;
//	
//	public AdminUserController(UserService userService) {
//		this.userSerivce = userService;
//	}
//	
//	// 사용자 목록 
//	@GetMapping(value="/user")
//	public ResponseEntity<?> userList(){
//		log.debug("listUser call");
//		try {
//			List<UserDto> list = userSerivce.listUser(null);
//			if(list != null && !list.isEmpty()) {
//				// HTTP 헤더 설정
//				HttpHeaders headers = new HttpHeaders();  
//				headers.setContentType(new MediaType("application","json",StandardCharsets.UTF_8));  // 컨텐츠 타입 : application/json 
//				return ResponseEntity.ok().headers(headers).body(list);
//			}else {
//				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//			}
//			
//		}catch(Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	// 회원의 정보를 받아 데이터베이스에 등록
//	@PostMapping("/user")
//	public ResponseEntity<?> userRegist(@RequestBody(required=true, content = @Content(schema = @Schema(implementation = UserDto.class))) @org.springframework.web.bind.annotation.RequestBody UserDto userDto){
//		log.debug("userRegist : {}",userDto);
//		try {
//			userSerivce.joinUser(userDto);
//			List<UserDto> list = userSerivce.listUser(null);
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(new MediaType("application","json",StandardCharsets.UTF_8));
//			return ResponseEntity.ok().headers(headers).body(list);
//		}catch(Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
//	// 사용자 정보 조회
//	@GetMapping(value = "/user/{userid}")
//	public ResponseEntity<?> userSelect(@Parameter(required=true) @PathVariable("userid") String userId){
//		log.debug("userSelect : {}",userId);
//		try {
//			UserDto userDto = userSerivce.selectUser(userId);
//			if(userDto != null) {
//				HttpHeaders headers = new HttpHeaders();
//				headers.setContentType(new MediaType("application","json",StandardCharsets.UTF_8));
//				return ResponseEntity.ok().headers(headers).body(userDto);
//			}else {
//				return ResponseEntity.noContent().build();
//			}
//		}catch(Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
//	// 사용자 정보 삭제
//	@DeleteMapping("/user/{userid}")
//	public ResponseEntity<?> userDelete(@Parameter(required=true) @PathVariable("userid") String userId){
//		log.debug("userDelete : {}",userId);
//		try {
//			userSerivce.deleteUser(userId);
//			List<UserDto> list = userSerivce.listUser(null);
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(new MediaType("application","json",StandardCharsets.UTF_8));
//			return ResponseEntity.ok().headers(headers).body(list);
//			
//		}catch(Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
//	private ResponseEntity<String> exceptionHandling(Exception e) {
//		e.printStackTrace();
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
//	}
	
}
