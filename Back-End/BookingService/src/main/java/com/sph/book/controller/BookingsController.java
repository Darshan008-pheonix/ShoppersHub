package com.sph.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sph.book.dto.CheckoutRequestDto;
import com.sph.book.dto.OrderRequestDto;
import com.sph.book.service.BookingsService;
import com.sph.util.dto.ResponseDto;

@RestController
@RequestMapping("/book")
public class BookingsController {
	
	@Autowired
	BookingsService bookingsService;
	
	
	  @GetMapping("/checkout")
	    public ResponseDto<?> checkout(@RequestBody CheckoutRequestDto request) {
	        return bookingsService.checkout(request);
	    }
	  
	  @PostMapping("/orderNow")
	  public ResponseDto<?> OrderProduct(@RequestBody OrderRequestDto request){
		  return bookingsService.orderProduct(request);
	  }
	  

}

