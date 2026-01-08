package com.sph.notification.serviceimpln;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sph.notification.dto.MailInfo;
import com.sph.notification.dto.OwnerOnboardingEvent;
import com.sph.notification.service.MailService;
import com.sph.util.dto.OwnerOnbaordingDto;

@Service
public class MailServiceImpln implements MailService{
	
	@Autowired
	JavaMailSender mail;

	@Override
	public String sendMail(MailInfo info) {
		SimpleMailMessage s=new SimpleMailMessage();  //MimeMessage
		s.setFrom("www.555darshanrdn@gmail.com");
		s.setTo(info.To());
		s.setSubject(info.Sub());
		s.setText(info.msg());
		mail.send(s);
		System.out.println("Send...!!!!");
		return "Mail Sent Successfully";
	}

	@Override
	public String sendOnboardingEmail(OwnerOnbaordingDto event) {
		SimpleMailMessage s=new SimpleMailMessage();  //MimeMessage
		s.setFrom("www.555darshanrdn@gmail.com");
		s.setTo(event.getEmail());
		s.setSubject("Onboarding message");
		s.setText("Hi "+event.getName()+" Welcome to ShoppersHub.com.Please find yours userid below:\nID:"+event.getOwnerId());
		mail.send(s);
		System.out.println("Send...!!!!");
		return "Mail Sent Successfully";
	}

}
