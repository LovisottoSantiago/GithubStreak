package com.lovisotto.githubstreak;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GithubstreakApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubstreakApplication.class, args);
	}

	@GetMapping("/{userName}")
	public ResponseEntity<String> scrapeUrl(@PathVariable String userName) {
		try {
			Scraper scraper = new Scraper("https://github.com/" + userName);
			int totalCommits = scraper.getData();

			String svgImage = generateSvgImage(totalCommits);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("image/svg+xml"));
			return ResponseEntity.ok().headers(headers).body(svgImage);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().body("<svg xmlns='http://www.w3.org/2000/svg' width='100' height='100'><text x='10' y='20'>0 days.</text></svg>");
		}
	}
	
	private String generateSvgImage(int totalCommits) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
           "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"17.5rem\" height=\"7rem\">" +
           "<style>" +
           ".background { fill: #0C0C0C; rx: 1.5rem;}" +
           ".text { fill: white; font-size: 20px; font-family: sans-serif; text-anchor: middle; dominant-baseline: middle; }" +
           "</style>" +
           "<rect width=\"100%\" height=\"100%\" class=\"background\" />" +
           "<text x=\"50%\" y=\"50%\" class=\"text\">" + "Current streak: " + totalCommits + " days!" + "</text>" +
           "</svg>";
	}



	


}
