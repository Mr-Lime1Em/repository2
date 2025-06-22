package com.example.Shop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shop.entity.UserEntity;
import com.example.Shop.jwt.SecurityUtil;
import com.example.Shop.request.UserLoginRequest;
import com.example.Shop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public void register(@RequestBody @Valid UserEntity userEntity) {
		userService.register(userEntity);
	}
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequest userLoginRequest) {
		String token = userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());

		return ResponseEntity.ok(Map.of("token", token));
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request) {
	    String header = request.getHeader("Authorization");
	    String token = header.substring(7);
	}
	
	@GetMapping("/me")
	public UserEntity me() {
	    String username = SecurityUtil.getCurrentUsername();
	    return userService.getUserInfo(username);
	}
}