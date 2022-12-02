package com.sid.UrlShortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sid.UrlShortener.entity.Url;
import com.sid.UrlShortener.repository.UrlRepository;
import static com.sid.UrlShortener.logic.GenerateShortUrl.getShortUrl;
import static com.sid.UrlShortener.logic.GenerateShortUrl.isUrlValid;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository urlRepository;
	
	public String getOrginalUrl(String shortUrl) {
		return urlRepository.findOrginalUrlByShortUrl(shortUrl);
	}
	
	public String urlExists(String originalUrl) {
		return urlRepository.findShortUrl(originalUrl);
		
	}
	public Url generateShortUrl(String url) {
		if(! isUrlValid(url)) {
			return null;
		}
		String urlExists =urlExists(url) ;
		if (urlExists!=null) {
			Url urlObject = new Url();
			urlObject.setOriginalUrl(url);
			urlObject.setShortUrl(urlExists);
			return urlObject;
	
		}
		Url urlObject = new Url();
		urlObject.setOriginalUrl(url);
		urlObject.setShortUrl(getShortUrl(url));
		return urlRepository.save(urlObject);
	}

}
