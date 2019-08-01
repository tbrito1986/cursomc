package com.thiagobrito.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.thiagobrito.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
