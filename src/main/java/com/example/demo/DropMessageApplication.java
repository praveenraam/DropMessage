package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class DropMessageApplication {

	@CrossOrigin
	public static void main(String[] args) {
		SpringApplication.run(DropMessageApplication.class, args);
	}

}