package com.desafio;

import javax.annotation.security.RunAs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.desafio.resource")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
