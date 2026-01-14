package com.sph.payment.serviceimpln;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sph.payment.entity.Payment;
import com.sph.payment.entity.Receipt;
import com.sph.payment.repo.PaymentRepository;
import com.sph.payment.repo.ReceiptRepo;
import com.sph.payment.service.PaymentService;
import com.sph.util.dto.OrderRequestDto;
import com.sph.util.dto.ResponseDto;
import com.sph.util.model.PaymentStatus;
import com.sph.util.service.CommonUtils;


@Service
public class PaymentServiceImpln implements PaymentService {

	/*
	 * Generate transaction ID
	 * Generate payment object
	 */
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ReceiptRepo receiptRepo;
	
	
	/*
	 * Configuration of database
	 * Produce Message 
	 * 
	 */
	
	
	@Override
	public ResponseDto<?> processPayment(OrderRequestDto request) {
	
		
		System.out.println("In Payment Service");
		// Generate payment object
		Payment payment = new Payment();
		
		payment.setTransactionId(UUID.randomUUID().toString());
		payment.setBookingId(request.getBookingId());
		payment.setPaymentType(request.getPaymentType());
		payment.setPaymentStatus(PaymentStatus.SUCCESS);
		payment.setAmount(request.getTotalCost());
		payment.setCreatedAt(Instant.now());
		
		
		
		
		Receipt receipt = new Receipt();
		receipt.setBillingAddress(request.getBillingAddress());
		receipt.setBookingId(request.getBookingId());
		receipt.setDeliveryAddress(request.getDeliveryAddress());
		receipt.setDeliveryCharge(request.getDeliveryfee());
		receipt.setItemPrice(request.getItemPrice());
		receipt.setPaymentTransactionId(payment.getTransactionId());
		receipt.setPaymentType(payment.getPaymentType());
		receipt.setTotalAmount(payment.getAmount());
		receipt.setTaxAmount(request.getTax());
		receipt.setUserId(request.getUserId());
		receipt.setGeneratedAt(Instant.now());
		
		Receipt receipt2=receiptRepo.save(receipt);
		payment.setReceiptId(receipt2.getReceiptId());
		paymentRepository.save(payment);
		return CommonUtils.prepareResponse("Payment processed successfully",payment.getTransactionId(),HttpStatus.BAD_REQUEST.value());
	}

}
