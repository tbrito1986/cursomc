package com.thiagobrito.cursomc.services;

import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractEmailService{

	private static final Logger LOG  = org.slf4j.LoggerFactory.getLogger(MockMailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		
	}

	
}
