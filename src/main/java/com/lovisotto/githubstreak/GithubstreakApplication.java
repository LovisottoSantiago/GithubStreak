package com.lovisotto.githubstreak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GithubstreakApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubstreakApplication.class, args);
	}

	@GetMapping("/nombre")
	public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
		return String.format("Hello %s!", name); //http://localhost:8080/nombre?name=Lovi
	}
	

}
