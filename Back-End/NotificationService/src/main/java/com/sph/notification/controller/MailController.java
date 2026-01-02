package com.sph.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sph.notification.dto.MailInfo;
import com.sph.notification.service.MailService;

@RestController
public class MailController {
	
	@Autowired
	MailService service;
	
	@PostMapping("/mail")
	String sendMail(@RequestBody MailInfo info) {
		return service.sendMail(info);
		
	}
	

}
