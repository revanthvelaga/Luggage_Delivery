package com.example.luggagedelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.luggagedelivery", "your.package.containing.SecurityConfig"})
public class LuggagedeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuggagedeliveryApplication.class, args);
	}

}
