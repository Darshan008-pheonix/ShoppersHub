package com.sph.user.service;

import java.util.List;

import com.sph.user.dto.UserRequestDto;
import com.sph.user.dto.UserResponseDto;
import com.sph.util.dto.ResponseDto;

public interface UserService {

	ResponseDto<Object> createUser(UserRequestDto request);

	UserResponseDto getUserById(String id);

	List<UserResponseDto> getAllUsers();

	UserResponseDto updateUser(String id, UserRequestDto request);

	ResponseDto<Object> deleteUser(String id);

}
