package com.sph.owner.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import org.springframework.util.IdGenerator;

import com.sph.owner.entity.Owner;
import com.sph.owner.entity.OwnerIdGenerator;
import com.sph.owner.exception.OwnerNotFoundException;
import com.sph.owner.repo.OwnerIdGeneratorRepo;
import com.sph.owner.repo.OwnerRepo;
import com.sph.util.dto.ResponseDto;

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
	OwnerRepo ownerRepo;
	
	@InjectMocks
	OwnerServiceImpl impl;
	
	
	OwnerIdGenerator ownerId1;
	
	OwnerIdGenerator ownerId2;
	
	Owner owner;
	
	@BeforeEach
	void beforeEach() {
		ownerId1=new OwnerIdGenerator();
		ownerId1.setId(1);
		ownerId2=new OwnerIdGenerator();
		ownerId2.setId(122);
		
		
		owner=new Owner();
		owner.setEmail("abc@gmail.com");
		owner.setOwnerName("abc");
		owner.setOwnerId("OWN001");
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
		assertEquals("abc@gmail.com",((Owner)(response.getData())).getEmail());
		assertEquals(302,response.getStatus());
	}
	
	@Test
	void test4() {
		Mockito.when(ownerRepo.findById("OWN7656")).thenReturn(Optional.empty());
		
		assertThrows(OwnerNotFoundException.class, ()->impl.findOwnerById("OWN7656"));
	}
	
	
	

}
