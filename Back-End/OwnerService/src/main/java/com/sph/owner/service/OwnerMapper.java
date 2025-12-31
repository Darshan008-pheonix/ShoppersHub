package com.sph.owner.service;

import org.mapstruct.Mapper;

import com.sph.owner.dto.OwnerDto;
import com.sph.owner.entity.Owner;

@Mapper(componentModel = "spring", uses = OwnerBankAccountMapper.class)
public interface OwnerMapper {
	
	
	Owner toEntity(OwnerDto dto);

    OwnerDto toDto(Owner owner);
	

}
