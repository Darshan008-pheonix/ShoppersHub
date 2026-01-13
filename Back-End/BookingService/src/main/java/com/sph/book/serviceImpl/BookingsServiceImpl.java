package com.sph.book.serviceImpl;

import java.time.Instant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.mongodb.client.MongoClient;
import com.sph.book.config.MongoConfig;
import com.sph.book.controller.BookingsController;
import com.sph.book.dao.BookingsDao;
import com.sph.book.dao.SagaCounterService;
import com.sph.book.dto.CheckoutRequestDto;
import com.sph.book.dto.OrderRequestDto;
import com.sph.book.dto.OrderResponseDto;
import com.sph.book.entity.BookingSaga;
import com.sph.book.entity.BookingStatus;
import com.sph.book.entity.Bookings;
import com.sph.book.entity.SagaStep;
import com.sph.book.service.BookingsService;
import com.sph.book.service.ProductClient;
import com.sph.util.dto.CheckoutResponseDto;
import com.sph.util.dto.PaymentAndRecpietDto;
import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;
import com.sph.util.model.PaymentStatus;
import com.sph.util.service.CommonUtils;

import tools.jackson.databind.ObjectMapper;

@Service
public class BookingsServiceImpl implements BookingsService{

    private final MongoClient mongoClient;

  
	
	@Autowired
	ProductClient productClient;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	BookingsDao bookingsDao;
	
	@Autowired
	SagaCounterService sagaCounterService;


    BookingsServiceImpl(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    } 
 
	
	

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
                    .eta("3-5 Days").ownerId(dto.getOwnerId())
                    .build();
		return CommonUtils.prepareResponse("Product Is Available ", checkOutresponseDto,HttpStatus.OK.value() );
		}
	}

	
	String generateSagaId() {
		  long seq = sagaCounterService.getNextSequence("product");
	      return "SAGA" + String.format("%03d", seq);
	}

	@Override
	public ResponseDto<?> orderProduct(OrderRequestDto request) {
		
		BookingSaga bookingSaga=new BookingSaga();
		bookingSaga.setSagaId(generateSagaId());
		updateBookingSaga(bookingSaga,SagaStep.REVERSING_PRODUCT, null);
		ResponseDto<Object> response=productClient.reserveProduct(request.getProductId(), request.getQuantity());
		
		if(response.getMessage().equals(CommonUtils.Product_Reserved)) {
			updateBookingSaga(bookingSaga,SagaStep.PRODUCT_RESERVED, null);
			
			updateBookingSaga(bookingSaga,SagaStep.CREATING_BOOKING, null);
			
		try {
			Bookings book=new Bookings();
			book.setBookingId(request.getBookingId());
			book.setBookingStatus(BookingStatus.PAYMENT_PENDING);
			book.setCreatedAt(Instant.now());
			book.setOwnerId(request.getOwnerID());
			book.setPaymentStatus(PaymentStatus.PENDING);
			book.setQty(request.getQuantity());
			book.setTotalcost(request.getTotalCost());
			book.setUpdatedAt(Instant.now());
			book.setPaymentType(request.getPaymentType());
			Bookings obj = bookingsDao.createBooking(book);
			if(ObjectUtils.isEmpty(obj)) {
				throw new Exception();
			}
				request.setBookingId(obj.getBookingId());
				updateBookingSaga(bookingSaga,SagaStep.BOOKING_CREATED, null);

				request.setBookingId(obj.getBookingId());
				return CommonUtils.prepareResponse("Booking Created Payment Pending",request,HttpStatus.OK.value());
		}
		catch(Exception e) {
			updateBookingSaga(bookingSaga,SagaStep.FAILED, "Failed While Creating Booking");
			System.out.println("Releasing the Reserved Product....!!!");
			ResponseDto<Object> releaseReposnse=productClient.releaseProduct(request.getProductId(), request.getQuantity());
			if(releaseReposnse.getMessage().equals(CommonUtils.Product_Released)) {
				return CommonUtils.prepareResponse("Product Had Been Released...!!",null,HttpStatus.NOT_ACCEPTABLE.value());
			}
			else {
				updateBookingSaga(bookingSaga,SagaStep.FAILED, "Failed Creating Booking But Also Relase API Not Working...!!!");
				return CommonUtils.prepareResponse(releaseReposnse.getMessage(),null,releaseReposnse.getStatus());
			}
		}
		
		}
		else {
			updateBookingSaga(bookingSaga,SagaStep.FAILED,"Failed While Reserving Product..!!!");
			return CommonUtils.prepareResponse(response.getMessage(),null,response.getStatus());
		}
		
	}
	
	
	
	void updateBookingSaga(BookingSaga bookingSaga,SagaStep currentStep,String failureReason){
		bookingSaga.setCurrentStep(currentStep);
		bookingSaga.setFailureReason(failureReason);
		bookingSaga.setUpdatedAt(Instant.now());
		bookingsDao.updateSaga(bookingSaga);
	}
	

}
