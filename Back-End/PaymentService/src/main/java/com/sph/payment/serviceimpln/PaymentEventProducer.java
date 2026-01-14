package com.sph.payment.serviceimpln;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sph.util.dto.PaymentProcessingDto;
import com.sph.util.model.BookingStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventProducer {
	
	
	
	private final KafkaTemplate<String, PaymentProcessingDto> kafkaTemplate;
	
	final static  String topic = "Payment-Status";

	/*
	 * public void sendPaymentStatus(PaymentProcessingDto event) {
	 * event.setBookingStatus(BookingStatus.PAYMENT_COMPLETED);
	 * 
	 * kafkaTemplate.send(topic,event.getTranxId(),event);
	 * 
	 * }
	 */
	public void sendPaymentStatus(PaymentProcessingDto event) {
	    log.info("Sending payment event to Kafka: {}", event);

	    kafkaTemplate.send(topic, event.getTranxId(), event)
	        .whenComplete((res, ex) -> {
	            if (ex == null) {
	                log.info("Kafka message sent to partition {}", res.getRecordMetadata().partition());
	            } else {
	                log.error("Kafka send failed", ex);
	            }
	        });
	}

	
	

}
