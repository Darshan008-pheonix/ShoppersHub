package com.sph.user.dto;

import java.time.LocalDateTime;

import com.sph.user.entity.AccountStatus;
import com.sph.util.model.Address;

import lombok.Data;

@Data
public class UserResponseDto {
	
	private String userId;

    private String name;

    private String email;

    private String phone;

    private boolean isActive;

    private AccountStatus status;

    private LocalDateTime createdAt;

    private Address address;

}
