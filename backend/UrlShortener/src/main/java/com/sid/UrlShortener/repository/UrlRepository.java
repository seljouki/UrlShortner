package com.sid.UrlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sid.UrlShortener.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Integer>{
	
	@Query(value = "select originalUrl from Url where short_url = :shortUrl")
	String findOrginalUrlByShortUrl(String shortUrl);
	
	@Query(value = "select shortUrl from Url where original_url = :originalUrl")
	String findShortUrl(String originalUrl);
}
