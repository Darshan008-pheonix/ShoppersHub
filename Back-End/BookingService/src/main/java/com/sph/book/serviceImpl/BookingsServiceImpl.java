package com.sph.book.serviceImpl;

import java.time.Instant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.sph.book.controller.BookingsController;
import com.sph.book.dto.CheckoutRequestDto;
import com.sph.book.entity.BookingStatus;
import com.sph.book.entity.Bookings;

import com.sph.book.service.BookingsService;
import com.sph.book.service.ProductClient;
import com.sph.util.dto.CheckoutResponseDto;
import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;
import com.sph.util.service.CommonUtils;

import tools.jackson.databind.ObjectMapper;

@Service
public class BookingsServiceImpl implements BookingsService{

  
	
	@Autowired
	ProductClient productClient;

	@Autowired
	private ObjectMapper objectMapper;
 
	
	

	@Override
	public ResponseDto<?> checkout(CheckoutRequestDto request) {
		
		ResponseDto<Object> response = productClient.validateProduct(request.productId(),request.quantity());
		if(ObjectUtils.isEmpty(response)) {
			return CommonUtils.prepareResponse("Product Is Un-Available ",null,HttpStatus.NOT_FOUND.value() );
		}
		else{
			System.out.println(response.getData());
			ProductDTO dto = objectMapper.convertValue(
			        response.getData(),
			        ProductDTO.class
			);
			double price =dto.getPricing().getFinalPrice()*request.quantity();
	        double tax = price * 0.18;
	        double delivery = 40;
			CheckoutResponseDto checkOutresponseDto=new CheckoutResponseDto().builder().itemPrice(price)
                    .tax(tax)
                    .deliveryCharge(delivery)
                    .totalAmount(price + tax + delivery)
                    .eta("3-5 Days")
                    .build();
		return CommonUtils.prepareResponse("Product Is Available ", checkOutresponseDto,HttpStatus.OK.value() );
		}
	}

}
