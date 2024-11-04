package com.ssafy.trip.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/join")
    public ResponseEntity<String> join() {
        return ResponseEntity.ok("User registration page");
    }
    
    @GetMapping("/{userid}")
    public ResponseEntity<Integer> idCheck(@PathVariable("userid") String userId) throws Exception {
        log.debug("idCheck userid : {}", userId);
        int cnt = userService.idCheck(userId);
        return ResponseEntity.ok(cnt);
    }
    
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserDto userDto) {
        log.debug("userDto info : {}", userDto);
        try {
            userService.joinUser(userDto);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            log.error("Error during user registration", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during user registration");
        }
    }
	
	
}
