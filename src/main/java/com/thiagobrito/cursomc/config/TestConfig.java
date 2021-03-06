package com.thiagobrito.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.thiagobrito.cursomc.services.DBService;
import com.thiagobrito.cursomc.services.EmailService;
import com.thiagobrito.cursomc.services.MockMailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbservice;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbservice.instantiateTesTestDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}

}
