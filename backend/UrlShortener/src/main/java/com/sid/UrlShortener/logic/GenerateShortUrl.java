package com.sid.UrlShortener.logic;

import java.nio.charset.Charset;

import org.apache.commons.validator.routines.UrlValidator;

import com.google.common.hash.Hashing;

public class GenerateShortUrl {
	
	/*Utilisation de la librairie Gauva pour faire un hash de l'url */
	
	public static String getShortUrl(String url) {
		String shortUrl= Hashing.murmur3_32_fixed().hashString(url, Charset.defaultCharset()).toString();
		return shortUrl;
		
	}
	/*Methode pour verifier si l'url est valid ou non*/
	public static boolean isUrlValid(String url) {
		boolean result =UrlValidator.getInstance().isValid(url); 
		return result;
		
	}
}
