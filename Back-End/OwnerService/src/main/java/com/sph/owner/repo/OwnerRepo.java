package com.sph.owner.repo;

import org.springframework.http.ResponseEntity;

import com.sph.owner.entity.Owner;

public interface OwnerRepo {
	  ResponseEntity<Owner> findByEmail(String email);
	  
}
