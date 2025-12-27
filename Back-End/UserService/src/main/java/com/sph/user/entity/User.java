package com.sph.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
    @GeneratedValue
    private String id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String passwordHash;

    private String status; // ACTIVE, BLOCKED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
   // Address address;


}
