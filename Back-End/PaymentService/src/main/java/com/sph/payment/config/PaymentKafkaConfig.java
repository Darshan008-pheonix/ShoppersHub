package com.sph.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PaymentKafkaConfig {
	
	@Bean
	NewTopic paymentProcessingTopic(){
		return TopicBuilder.name("Payment-Status").partitions(2).replicas(1).build();
		
	}

}
