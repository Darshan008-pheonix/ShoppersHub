package com.sph.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sph.user.dto.UserRequestDto;
import com.sph.user.dto.UserResponseDto;
import com.sph.user.service.UserService;
import com.sph.util.dto.ResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseDto<Object> createUser(@Valid @RequestBody UserRequestDto request) {
		return userService.createUser(request);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable String id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable String id,@Valid @RequestBody UserRequestDto request) {
		return ResponseEntity.ok(userService.updateUser(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseDto<Object> deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
	
}