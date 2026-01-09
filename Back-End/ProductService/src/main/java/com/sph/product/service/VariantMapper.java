package com.sph.product.service;

import org.mapstruct.Mapper;

import com.sph.product.entity.Variant;
import com.sph.util.dto.VariantDTO;

@Mapper(componentModel = "spring")
public interface VariantMapper {

	VariantDTO toDto(Variant variant);
	Variant toEntity(VariantDTO dto);
}
