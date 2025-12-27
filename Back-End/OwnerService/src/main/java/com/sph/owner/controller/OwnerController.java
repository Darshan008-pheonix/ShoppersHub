package com.sph.owner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sph.owner.dto.OwnerDto;
import com.sph.owner.dto.OwnerSearchDto;
import com.sph.owner.service.OwnerService;
import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/owner")
public class OwnerController {
	
	@Autowired
	OwnerService ownerService;

		@PostMapping("/add")
	    public ResponseDto<Object> addOwner(
	            @Valid @RequestBody OwnerDto dto) {
	        return ownerService.addOwner(dto);
	    }

	    @PutMapping("/{id}")
	    public ResponseDto<Object> updateOwner(
	            @PathVariable String id,
	            @Valid @RequestBody OwnerDto dto) {
	        return ownerService.updateOwner(id, dto);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseDto<Object> deleteOwner(@PathVariable String id) {
	        return ownerService.deleteOwner(id);
	    }


	    @PostMapping("/search")
	    public ResponseEntity<ResponseDto<List<ResponseDto>>> searchOwner( @Valid @RequestBody OwnerSearchDto dto) {
	    	
	        return ownerService.searchOwners(dto);
	    }

	    
	    @PostMapping(value ="/addProduct")
		ResponseDto<Object> addProductByOwner( @RequestBody ProductDTO productDTO){
			return  ownerService.addProductByOwner(productDTO);
	    	
	    }
}
