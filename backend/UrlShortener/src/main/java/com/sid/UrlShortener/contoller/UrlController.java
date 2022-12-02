package com.sid.UrlShortener.contoller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sid.UrlShortener.entity.Url;
import com.sid.UrlShortener.service.UrlService;

@RestController
@RequestMapping("url/shortner")
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController {

	@Autowired
	private UrlService urlService;
	
	@GetMapping("/{shortUrl}")
	public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) throws URISyntaxException { 
		String originalUrl =urlService.getOrginalUrl(shortUrl);
		if(originalUrl!=null) {
			URI uri = new URI(originalUrl);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uri);
			return new ResponseEntity<String>(httpHeaders,HttpStatus.SEE_OTHER);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST); 
}
	@PostMapping
	public ResponseEntity<Url> generateShortUrl(@RequestBody String url) {
		Url shortUrl= urlService.generateShortUrl(url);
		if(shortUrl!=null) {
			return new ResponseEntity<Url>(shortUrl,HttpStatus.OK);
		}
		return new ResponseEntity<Url>(HttpStatus.BAD_REQUEST);
	
}
	
}