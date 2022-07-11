package com.example.neprep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@EnableWebSecurity
@SpringBootApplication
public class NeprepApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeprepApplication.class, args);
	}

}
