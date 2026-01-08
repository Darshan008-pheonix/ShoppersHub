package com.sph.notification.service;

import com.sph.notification.dto.MailInfo;
import com.sph.notification.dto.OwnerOnboardingEvent;
import com.sph.util.dto.OwnerOnbaordingDto;

public interface MailService {
	
	String sendMail(MailInfo info);
	
	String sendOnboardingEmail(OwnerOnbaordingDto event);

}
