package com.sph.owner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.sph.owner.entity.Owner;

public interface OwnerRepo extends JpaRepository<Owner, String>{
	
	  ResponseEntity<Owner> findByEmail(String email);
	  
	  ResponseEntity<Owner> findByPhoneNumber(String phoneNumber);
	  
}
