package com.sph.product.service;

import org.mapstruct.Mapper;

import com.sph.product.entity.Pricing;
import com.sph.util.dto.PricingDTO;
import com.sph.util.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface PricingMapper {

	PricingDTO toDto(Pricing pricing);
	
	Pricing toEntity(PricingDTO dto);
	
}
