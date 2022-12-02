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
	/*cette methode fait un select par original url*/
	public String getOrginalUrl(String shortUrl) {
		return urlRepository.findOrginalUrlByShortUrl(shortUrl);
	}
	/*cette methode verifie si l'url existe deja*/
	public String urlExists(String originalUrl) {
		return urlRepository.findShortUrl(originalUrl);
		
	}
	/*cette methode verifie si l'url est valide ou non, 
	 * puis s'il existe deja dans la base donnees avant de cree un nouveau shorturl.
	 *  s'il existe deja on utilse l'ancien    */
	
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
