package com.sph.owner.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.discovery.shared.Application;
import com.sph.owner.dto.OwnerDto;
import com.sph.owner.service.OwnerService;
import com.sph.util.dto.ResponseDto;

import jakarta.validation.Valid;







@WebMvcTest(OwnerController.class)
class OwnerControllerTest {
	
@Autowired
private MockMvc mockMvc;
	
@MockitoBean
private OwnerService ownerService;






/*
 * 	@PostMapping("/add")
	    public ResponseDto<Object> addOwner(
	            @Valid @RequestBody OwnerDto dto) {
	        return ownerService.addOwner(dto);
	    }
 */

@Test
void addOwnerTest1() throws Exception{
	ResponseDto<Object> response =new ResponseDto<>("Owner created",null,201);
	
	
	
	
	Mockito.when(ownerService.addOwner(any())).thenReturn(response);
	
	 mockMvc.perform(post("/owner/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content("""
	                 {
  "ownerName": "Darshan",
  "email": "darshan@test.com",
  "phoneNumber": "9876543210",
  "companyName": "SPH Pvt Ltd",
  "gstNumber": "29ABCDE1234F1Z5",
  "status": "ACTIVE",
  "address": {
    "doorno": "12A",
    "addressLine1": "MG Road",
    "addressLine2": "Near Metro",
    "city": "Bangalore",
    "state": "KA",
    "landmark": "Metro Station",
    "pincode": 560001
  },
  "ownerAccount": {
    "bankName": "HDFC Bank",
    "branchName": "MG Road",
    "ifscCode": "HDFC0123456",
    "branch": "MG Road",
    "uiId": "darshan@hdfc",
    "status": "ACTIVE"
  }
}
	            """))
	           .andExpect(status().isOk())
	           .andExpect(jsonPath("$.message")
	                   .value("Owner created"))
	           .andExpect(jsonPath("$.status").value(201));
			
	
}


@Test
void addOwnerTest2() throws Exception{
	ResponseDto<Object> response =new ResponseDto<>("Owner created",null,201);
	
	
	
	
	Mockito.when(ownerService.addOwner(any())).thenReturn(response);
	
	 mockMvc.perform(post("/owner/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content("""
	                 {
  "ownerName": "Darshan",
  "email": "darshan@test.com",
  "phoneNumber": "9876543210",
  "companyName": "SPH Pvt Ltd",
  "gstNumber": "29ABCDE1234F1",
  "status": "ACTIVE",
  "address": {
    "doorno": "12A",
    "addressLine1": "MG Road",
    "addressLine2": "Near Metro",
    "city": "Bangalore",
    "state": "KA",
    "landmark": "Metro Station",
    "pincode": 560001
  },
  "ownerAccount": {
    "bankName": "HDFC Bank",
    "branchName": "MG Road",
    "ifscCode": "HDFC0123456",
    "branch": "MG Road",
    "uiId": "darshan@hdfc",
    "status": "ACTIVE"
  }
}
	            """))
	           .andExpect(status().isBadRequest());
			
	
}
/*
 *  @DeleteMapping("/{id}")
	    public ResponseDto<Object> deleteOwner(@PathVariable String id) {
	        return ownerService.deleteOwner(id);
 */

void test3() throws Exception {
	ResponseDto<Object> response =new ResponseDto<>("Owner Deleted",null,200);
	mockMvc.perform(delete("owner/OWN001")).andExpect(null);
}

/*
 *  @GetMapping("/info")
	    String getInfo(@RequestParam String msg) {
	    	return "In Onwer Info Method";
	    }
 */
void test4() throws Exception {

	mockMvc.perform(get("/owner/info").param("msg", "JSHDgasjkdg")
			);
}



/*
 * 
	    @PutMapping("/{id}")
	    public ResponseDto<Object> updateOwner(
	            @PathVariable String id,
	            @Valid @RequestBody OwnerDto dto) {
	        return ownerService.updateOwner(id, dto);
	    }

 */


void test5() throws Exception {

	mockMvc.perform(put("/OWN001").contentType(MediaType.APPLICATION_JSON).content("""
			
			
			
			
			
			
			""")).andExpect(null);
}
}
