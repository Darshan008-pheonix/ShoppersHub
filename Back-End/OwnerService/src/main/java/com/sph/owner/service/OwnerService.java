package com.sph.owner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sph.owner.dto.OwnerDto;
import com.sph.owner.dto.OwnerSearchDto;
import com.sph.util.dto.ResponseDto;



public interface OwnerService {
		ResponseDto<Object> addOwner(OwnerDto dto);

	    ResponseDto<Object> updateOwner(
	            String ownerId, OwnerDto dto);

	    ResponseDto<Object> deleteOwner(String ownerId);

	    ResponseDto<Object> getOwnerById(String ownerId);

	    ResponseEntity<ResponseDto<Object>> searchOwners(
	            OwnerSearchDto searchDto);

		ResponseDto<Object> getAllOwners();

}
