package com.sph.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sph.user.entity.IdGeneration;

public interface IdGenerationRepo extends JpaRepository<IdGeneration, Integer> {

}
