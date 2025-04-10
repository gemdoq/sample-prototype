package com.sample.sampleprototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SamplePrototypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplePrototypeApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello, CI/CD! webhook triggered!";
	}

}
