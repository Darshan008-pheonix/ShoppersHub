package com.sph.product.service;

import org.mapstruct.Mapper;

import com.sph.product.entity.Inventory;
import com.sph.util.dto.InventoryDTO;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

	
	Inventory toEntity(InventoryDTO dto);
	
	InventoryDTO toDto(Inventory inventory);
	
	
}
