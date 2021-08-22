package io.github.paulamuhlbach.tafapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class TafApplication {
	
	@GetMapping("/")
	public String hello() {
		return "Hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(TafApplication.class, args);
	}

}
