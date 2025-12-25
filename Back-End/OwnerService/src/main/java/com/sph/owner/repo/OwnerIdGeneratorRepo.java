package com.sph.owner.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sph.owner.entity.OwnerIdGenerator;

public interface OwnerIdGeneratorRepo extends JpaRepository<OwnerIdGenerator, Integer> {
}
