package com.sph.user.dto;

import java.time.LocalDateTime;

public class UserDto {

	private String name;
	private String email;
	private String phone;
	private String passwordHash;
	private String status; // ACTIVE, BLOCKED
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	

}
