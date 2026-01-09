package com.sph.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sph.product.service.IProductService;
import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;

import jakarta.validation.Valid;
import tools.jackson.databind.ObjectMapper;

@RestController
public class ProductController {
	
	
	@Autowired
	IProductService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@PostMapping(value ="/addProduct")
	ResponseDto<Object> addProduct( @RequestBody ProductDTO productDTO){
		return service.addProduct(productDTO);
	}
	
	@GetMapping("/validateProduct")
	ResponseDto<Object> validateProduct(@RequestParam String pid,@RequestParam int qnt ){
		return service.validateProduct(pid,qnt);
	}
	
	
	@PatchMapping("/reserveProduct")
	ResponseDto<Object> reserveProduct(@RequestParam String pid,@RequestParam int qnt ){
		return service.reserveProduct(pid,qnt);
	}

	
	
}
