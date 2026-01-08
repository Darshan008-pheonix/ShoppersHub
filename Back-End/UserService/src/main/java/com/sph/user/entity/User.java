package com.sph.user.entity;

import java.time.LocalDateTime;

import com.sph.util.model.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
    private String userId;

	@Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 15)
    private String phone;

    @Column(nullable = false)
    private String password;

    private boolean isActive; 
    
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private LocalDateTime createdAt;
   
    @Embedded
    private Address address;


}
