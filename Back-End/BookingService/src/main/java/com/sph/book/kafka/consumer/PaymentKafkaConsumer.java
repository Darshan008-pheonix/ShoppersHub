package com.sph.book.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sph.book.service.ReceiptService;
import com.sph.util.dto.PaymentAndRecpietDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentKafkaConsumer{
	
	private final ReceiptService receiptService;
	
	@KafkaListener(topics = "Payment-Status", groupId = "booking-group")
	public String sendReceipt(PaymentAndRecpietDto event) {
		
	return	receiptService.sendReceipt(event);

	}	
}

