package com.sph.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerOnboardingEvent {
	
	private String ownerId;
	private String name;
	private String email;

}
