package com.farmacia.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class FarmaciafactApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(FarmaciafactApplication.class, args);
	}

}
