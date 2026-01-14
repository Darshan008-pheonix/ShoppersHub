package com.sph.owner.serviceImpl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sph.util.dto.OwnerOnbaordingDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerEventProducer {

	private final KafkaTemplate< String,OwnerOnbaordingDto> kafkaTemplate;
	
	String topic="Owner-Onboarding";
	
	public void sendOnboardingMessage(OwnerOnbaordingDto event) {
		kafkaTemplate.send(topic,event.getOwnerId(),event);
	}

}
