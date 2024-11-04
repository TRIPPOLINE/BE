package com.ssafy.trip.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminUserController {
	private final UserService userSerivce;
	
	public AdminUserController(UserService userService) {
		this.userSerivce = userService;
	}
	
}
