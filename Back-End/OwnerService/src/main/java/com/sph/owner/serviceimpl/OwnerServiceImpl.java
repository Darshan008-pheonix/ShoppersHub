package com.sph.owner.serviceimpl;


import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.sph.owner.dto.OwnerDto;
import com.sph.owner.dto.OwnerSearchDto;
import com.sph.owner.entity.Owner;
import com.sph.owner.entity.OwnerIdGenerator;
import com.sph.owner.entity.OwnerStatus;
import com.sph.owner.exception.OwnerNotFoundException;
import com.sph.owner.repo.OwnerIdGeneratorRepo;
import com.sph.owner.repo.OwnerRepo;
import com.sph.owner.service.OwnerService;
import util.model.ResponseDto;
import util.model.utils;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerRepo ownerRepo;

	@Autowired
	private OwnerIdGeneratorRepo idGeneratorRepo;

	private static final ModelMapper mapper = new ModelMapper();


	@Override
	public ResponseDto<Object> addOwner(OwnerDto dto) {

		Owner owner = mapper.map(dto, Owner.class);

		String ownerId = generateOwnerId();
		owner.setOwnerId(ownerId);
		owner.setStatus(OwnerStatus.ACTIVE);
		owner.setBlocked(false);

		Owner savedOwner = ownerRepo.save(owner);
		ResponseDto responseDto = mapper.map(savedOwner, ResponseDto.class);

		return utils.prepareResponse("Owner created successfully", responseDto, HttpStatus.CREATED.value());
	}


	private String generateOwnerId() {

		OwnerIdGenerator idGen = idGeneratorRepo.save(new OwnerIdGenerator());
		int id = idGen.getId();

		if (id < 10) {
			return "own001" + id;
		} else if (id < 100) {
			return "own01" + id;
		}
		return "own1" + id;
	}


	@Override
	public ResponseDto<Object> getAllOwners() {

		List<Owner> owners = ownerRepo.findAll();
		List<ResponseDto> responseList = new ArrayList<>();

		for (Owner owner : owners) {
			responseList.add(mapper.map(owner, ResponseDto.class));
		}

		return utils.prepareResponse("Owners fetched successfully", responseList, HttpStatus.OK.value());
	}


	@Override
	public ResponseDto<Object> getOwnerById(String ownerId) {

		Owner owner = ownerRepo.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException("Owner not found"));

		ResponseDto dto = mapper.map(owner, ResponseDto.class);

		return utils.prepareResponse("Owner fetched successfully", dto, HttpStatus.FOUND.value());
	}


	@Override
	public ResponseDto<Object> updateOwner(String ownerId, OwnerDto dto) {

		Owner owner = ownerRepo.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException("Owner not found"));

		owner.setOwnerName(dto.getOwnerName());
		owner.setCompanyName(dto.getCompanyName());
		owner.setEmail(dto.getEmail());
		owner.setPhoneNumber(dto.getPhoneNumber());
		owner.setGstNumber(dto.getGstNumber());
		owner.setAddress(dto.getAddress());

		Owner updatedOwner = ownerRepo.save(owner);
		ResponseDto responseDto = mapper.map(updatedOwner, ResponseDto.class);

		return utils.prepareResponse("Owner updated successfully", responseDto, HttpStatus.OK.value());
	}


	@Override
	public ResponseDto<Object> deleteOwner(String ownerId) {

		Owner owner = ownerRepo.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException("Owner not found"));

		ownerRepo.delete(owner);

		return utils.prepareResponse("Owner deleted successfully", null, HttpStatus.OK.value());
	}


	@Override
	public ResponseEntity<ResponseDto<List<ResponseDto>>> searchOwners(
	        OwnerSearchDto searchDto) {

	    if (searchDto == null) {
	        return ResponseEntity.badRequest().body(
	        		utils.prepareResponse(
	                        "Search criteria must not be null",
	                        null,
	                        HttpStatus.BAD_REQUEST.value()
	                )
	        );
	    }

	    List<ResponseDto> result = ownerRepo.findAll()
	            .stream()
	            .filter(owner -> matches(searchDto.getOwnerId(), owner.getOwnerId()))
	            .filter(owner -> matches(searchDto.getOwnerName(), owner.getOwnerName()))
	            .filter(owner -> matches(searchDto.getCompanyName(), owner.getCompanyName()))
	            .filter(owner -> matches(searchDto.getEmail(), owner.getEmail()))
	            .filter(owner -> matches(searchDto.getPhoneNumber(), owner.getPhoneNumber()))
	            .filter(owner -> matches(searchDto.getGstNumber(), owner.getGstNumber()))
	            .map(owner -> mapper.map(owner, ResponseDto.class))
	            .toList();

	    return ResponseEntity.ok(
	    		utils.prepareResponse(
	                    "Owners fetched successfully",
	                    result,
	                    HttpStatus.OK.value()
	            )
	    );
	}

	private boolean matches(String search, String actual) {
	    return ObjectUtils.isEmpty(search)
	            || (actual != null && actual.equalsIgnoreCase(search));
	}

	private boolean matches(Object search, Object actual) {
	    return ObjectUtils.isEmpty(search)
	            || (actual != null && actual.equals(search));
	}

	
}
