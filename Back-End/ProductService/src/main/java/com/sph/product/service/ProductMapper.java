package com.sph.product.service;

import org.mapstruct.Mapper;

import com.sph.product.entity.Product;
import com.sph.util.dto.ProductDTO;

@Mapper(componentModel = "spring" ,uses = {InventoryMapper.class,PricingMapper.class,VariantMapper.class}) 
public interface ProductMapper {
	
	
	Product toEntity(ProductDTO dto);
	
	ProductDTO toDto(Product product);

}
