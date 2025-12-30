package com.sph.owner.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.IdGenerator;

import com.sph.owner.dto.OwnerDto;
import com.sph.owner.dto.OwnerSearchDto;
import com.sph.owner.entity.Owner;
import com.sph.owner.entity.OwnerIdGenerator;
import com.sph.owner.entity.OwnerStatus;
import com.sph.owner.exception.OwnerNotFoundException;
import com.sph.owner.repo.OwnerIdGeneratorRepo;
import com.sph.owner.repo.OwnerRepo;
import com.sph.util.dto.ResponseDto;
import com.sph.util.service.CommonUtils;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

	/*
	 * @Test ->to tell method is a test case
	 * @BeforeEach ->this will be executed before executing each test methods
	 * @AfterEach ->this will be executed after executing each test methods
	 * @BeforeAll(static) ->Will ge executed only once before all the test methods
	 * @AfterAll ->Will ge executed only once After all the test methods
	 * @Spy->Will create actual object
	 * @Mock->Will create dummy object we need give impln
	 */

	@Mock
	OwnerIdGeneratorRepo idGeneratorRepo;
	
	@Mock
	ModelMapper mapper;
	
	@Mock
	OwnerRepo  ownerRepo;
	
	@InjectMocks
	OwnerServiceImpl impl;
	
	
	
	OwnerIdGenerator ownerId1;
	
	OwnerIdGenerator ownerId2;
	
	OwnerDto dto;
	
	Owner owner;
	
	Owner owner2;
	
	Owner owner3;
	
	Owner owner4;
	
	OwnerSearchDto dto2;
	
	List<Owner> l1;
	
	@BeforeEach
	void beforeEach() {
		ownerId1=new OwnerIdGenerator();
		ownerId1.setId(1);
		ownerId2=new OwnerIdGenerator();
		ownerId2.setId(122);
		
		
		owner=new Owner();
		owner.setEmail("abcd@gmail.com");
		owner.setOwnerName("abc");
		owner.setOwnerId("OWN001");
		owner.setStatus(OwnerStatus.ACTIVE);
		owner.setPhoneNumber(123456789);
		owner.setBlocked(false);
		owner.setCompanyName("abc");
		owner.setGstNumber("GST5465456");
		
		owner2=new Owner();
		owner2.setEmail("abc@gmail.com");
		owner2.setOwnerName("abc");
		owner2.setOwnerId("OWN002");
		owner2.setStatus(OwnerStatus.ACTIVE);
		owner2.setBlocked(false);
		owner2.setCompanyName("xyz");
		owner2.setGstNumber("GST54654126");
		owner2.setPhoneNumber(1456789);
		
		owner3=new Owner();
		owner3.setEmail("abc@gmail.com");
		owner3.setOwnerName("abc");
		owner3.setOwnerId("OWN003");
		owner3.setStatus(OwnerStatus.ACTIVE);
		owner3.setBlocked(false);
		owner3.setCompanyName("abc");
		owner3.setGstNumber("GST54867456");
		owner3.setPhoneNumber(123485789);
		
		
		owner4=new Owner();
		owner4.setEmail("abc@gmail.com");
		owner4.setOwnerName("abc");
		owner4.setOwnerId("OWN004");
		owner4.setStatus(OwnerStatus.ACTIVE);
		owner4.setBlocked(false);
		owner4.setCompanyName("abc");
		owner4.setGstNumber("GST547567");
		owner4.setPhoneNumber(1246789);
		
		l1=new ArrayList<Owner>();
		l1.add(owner);
		l1.add(owner2);
		l1.add(owner3);
		l1.add(owner4);
		
		dto=new OwnerDto();
		dto.setEmail("abc@gmail.com");
		dto.setOwnerName("abc");
		dto.setOwnerId("OWN001");
		dto.setStatus(OwnerStatus.ACTIVE);
		dto.setBlocked(false);
		
		
		dto2=new  OwnerSearchDto();
		dto2.setOwnerId("OWN001");
	}
	
	
	@Test
	void Test1() throws Exception {
		
		Mockito.when(idGeneratorRepo.save(any(OwnerIdGenerator.class))).thenReturn(ownerId1);
		Method method =OwnerServiceImpl.class.getDeclaredMethod("generateOwnerId");
		method.setAccessible(true);
		String ans=(String)method.invoke(impl);
		assertEquals("OWN001",ans);
	}
	
	
//	@Test
//	void Test2() {
//		Mockito.when(idGeneratorRepo.save(any(OwnerIdGenerator.class))).thenReturn(ownerId2);
//		String ans=impl.generateOwnerId();
//		assertEquals("OWN122",ans);
//	}
	
	
	@Test
	void test3() {
		Mockito.when(ownerRepo.findById("OWN001")).thenReturn(Optional.of(owner));
		ResponseDto<Object> response = impl.findOwnerById("OWN001");
		
		assertEquals("Owner Found",response.getMessage());
		assertEquals("abcd@gmail.com",((Owner)(response.getData())).getEmail());
		assertEquals(302,response.getStatus());
	}
	
	@Test
	void test4() {
		Mockito.when(ownerRepo.findById("OWN7656")).thenReturn(Optional.empty());
		
		assertThrows(OwnerNotFoundException.class, ()->impl.findOwnerById("OWN7656"));
	}
	/*
	 * 	public ResponseDto<Object> addOwner(OwnerDto dto) {

		Owner owner = mapper.map(dto, Owner.class);

		String ownerId = generateOwnerId();
		owner.setOwnerId(ownerId);
		owner.setStatus(OwnerStatus.ACTIVE);
		owner.setBlocked(false);

		Owner savedOwner = ownerRepo.save(owner);
		ResponseDto responseDto = mapper.map(savedOwner, ResponseDto.class);

		return CommonUtils.prepareResponse("Owner created successfully", responseDto, HttpStatus.CREATED.value());
	}

	 */
	
	@Test
	void test5() {
		Mockito.when(mapper.map(dto,Owner.class)).thenReturn(owner);
		Mockito.when(idGeneratorRepo.save(any(OwnerIdGenerator.class))).thenReturn(ownerId1);
		Mockito.when(ownerRepo.save(owner)).thenReturn(owner);
		when(mapper.map(owner, OwnerDto.class)).thenReturn(dto);
		ResponseDto<Object> response = impl.addOwner(dto);
		assertEquals("Owner created successfully",response.getMessage());
		assertEquals("abc@gmail.com",((OwnerDto)(response.getData())).getEmail());
		assertEquals(201,response.getStatus());
		
		 verify(mapper).map(dto, Owner.class);
		 verify(ownerRepo).save(any(Owner.class));
		 verify(idGeneratorRepo,times(1)).save(any(OwnerIdGenerator.class));
		
	}
	
	
	/*
	 * @Override
	public ResponseDto<Object> searchOwners(
	        OwnerSearchDto searchDto) {

	    if (searchDto == null) {
	        return
	        		CommonUtils.prepareResponse(
	                        "Search criteria must not be null",
	                        null,
	                        HttpStatus.BAD_REQUEST.value()
	                );
	    }

	    
	    List<Owner> result = ownerRepo.findAll()
	            .stream()
	            .filter(owner -> matches(searchDto.getOwnerId(), owner.getOwnerId()))
	            .filter(owner -> matches(searchDto.getOwnerName(), owner.getOwnerName()))
	            .filter(owner -> matches(searchDto.getCompanyName(), owner.getCompanyName()))
	            .filter(owner -> matches(searchDto.getEmail(), owner.getEmail()))
	            .filter(owner -> matches(searchDto.getPhoneNumber(), owner.getPhoneNumber()))
	            .filter(owner -> matches(searchDto.getGstNumber(), owner.getGstNumber()))
	            .map(owner -> mapper.map(owner, Owner.class))
	            .toList();

	    return
	    		CommonUtils.prepareResponse(
	                    "Owners fetched successfully",
	                    result,
	                    HttpStatus.OK.value()
	            );
	}

	 */
	
	@Test
	void Test6(){
		
		ResponseDto<Object> response = impl.searchOwners(null);
		assertEquals("Search criteria must not be null",response.getMessage());
		assertEquals(null,(response.getData()));
		assertEquals(400,response.getStatus());
		
	}
	
	@Test
	void Test7() {
		
		when( ownerRepo.findAll()).thenReturn(l1);
		ResponseDto<Object> response = impl.searchOwners(dto2);
		assertEquals("Owners fetched successfully",response.getMessage());
		assertEquals(1,((List<Owner>)(response.getData())).size());
		assertEquals(200,response.getStatus());
	}
	
	
	@Test
	void Test8() {
		dto2=new OwnerSearchDto();
		dto2.setCompanyName("abc");
		when( ownerRepo.findAll()).thenReturn(l1);
		ResponseDto<Object> response = impl.searchOwners(dto2);
		assertEquals("Owners fetched successfully",response.getMessage());
		assertEquals(3,((List<Owner>)(response.getData())).size());
		assertEquals(200,response.getStatus());
	}
	
	
	@Test
	void Test9() {
		dto2=new OwnerSearchDto();
		dto2.setEmail("abcd@gmail.com");
		when( ownerRepo.findAll()).thenReturn(l1);
		ResponseDto<Object> response = impl.searchOwners(dto2);
		assertEquals("Owners fetched successfully",response.getMessage());
		assertEquals(1,((List<Owner>)(response.getData())).size());
		assertEquals("abcd@gmail.com",((List<Owner>)(response.getData())).get(0).getEmail());
		assertEquals(200,response.getStatus());
	}
	

}
