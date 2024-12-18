package com.lovisotto.githubstreak;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GithubstreakApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubstreakApplication.class, args);
	}

	@GetMapping("/streak/{userName}")
	public int scrapeUrl(@PathVariable String userName) {
		try {
			Scraper scraper = new Scraper("https://github.com/" + userName);
			int totalCommits = scraper.getData();
      
      return totalCommits;
		} 
    catch (IOException e) {
      return 0;
    }
	}


	


}
