package com.sph.owner.service;

import org.mapstruct.Mapper;

import com.sph.owner.dto.OwnerBankAccountDto;
import com.sph.owner.entity.OwnerBankAccount;

@Mapper(componentModel = "spring")
public interface OwnerBankAccountMapper {

    OwnerBankAccount toEntity(OwnerBankAccountDto dto);

    OwnerBankAccountDto toDto(OwnerBankAccount entity);
}

