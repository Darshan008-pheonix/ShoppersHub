package com.sph.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sph.user.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
	
	Optional<User> findByEmail(String email);

}
