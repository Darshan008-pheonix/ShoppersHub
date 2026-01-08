package com.sph.notification.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sph.notification.dto.OwnerOnboardingEvent;
import com.sph.notification.service.MailService;
import com.sph.util.dto.OwnerOnbaordingDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerOnboardingConsumer {
	
	private final MailService mailservice;
	
	@KafkaListener(topics = "Owner-Onboarding", groupId = "group1")
	public void consume1(OwnerOnbaordingDto event) {
		mailservice.sendOnboardingEmail(event);
	}

}
