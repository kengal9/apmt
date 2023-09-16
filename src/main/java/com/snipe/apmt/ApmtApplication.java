package com.snipe.apmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Configuration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.snipe.apmt")


public class ApmtApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApmtApplication.class, args);
		System.out.println("<-------------------------BOOTED-------------------------->");
	}
	

}



