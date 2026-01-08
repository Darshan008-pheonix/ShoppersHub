package com.sph.owner.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	
	@Bean
	NewTopic ownerOnboardingTopic() {
		return TopicBuilder.name("Owner-Onboarding").partitions(2).replicas(1).build();
	}
}
