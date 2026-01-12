package com.sph.product.service;

import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;

public interface IProductService {

	
	ResponseDto<Object> addProduct(ProductDTO productDTO);

	ResponseDto<Object> validateProduct(String pid, int qnt);

	ResponseDto<Object> reserveProduct(String pid, int qnt);

	ResponseDto<Object> releaseProduct(String pid, int qnt);
}
