package com.sph.user.service;

import com.sph.user.dto.UserDto;
import com.sph.user.entity.User;

public interface UserService {
	
	User createUser(UserDto dto);
	User getUser(String id);
	User getUserByEmail(String email);
	void updateUser(String id, UserDto request);
	void blockUser(String id);
	void activateUser(String id);

}
