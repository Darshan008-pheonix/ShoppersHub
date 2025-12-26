package com.sph.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sph.product.service.IProductService;
import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	
	@Autowired
	IProductService service;
	
	
	@PostMapping(value ="/addProduct")
	ResponseDto<Object> addProduct( @RequestBody ProductDTO productDTO){
		return service.addProduct(productDTO);
	}
	

	
	
	
}
