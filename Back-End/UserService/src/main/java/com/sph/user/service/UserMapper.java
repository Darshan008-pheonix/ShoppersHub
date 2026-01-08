package com.sph.user.service;

import java.util.List;

import org.mapstruct.Mapper;

import com.sph.user.dto.UserRequestDto;
import com.sph.user.dto.UserResponseDto;
import com.sph.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User toEntity(UserRequestDto dto);

	UserResponseDto toDto(User user);
	
	List<UserResponseDto> toDtoList(List<User> users);

}
