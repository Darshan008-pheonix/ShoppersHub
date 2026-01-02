package com.sph.notification.service;

import com.sph.notification.dto.MailInfo;

public interface MailService {
	
	String sendMail(MailInfo info);

}
