package com.sid.UrlShortener.contoller;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.sid.UrlShortener.entity.Url;
import com.sid.UrlShortener.service.UrlService;
import static com.sid.UrlShortener.logic.GenerateShortUrl.isUrlValid;
@WebMvcTest
public class UrlControllerTest {
	   @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private UrlService urlService;

	    @Test
	    void getOrginalUrlTest() throws Exception {
	        when(urlService.getOrginalUrl("50328aa4")).thenReturn("https://www.google.com");
	        mockMvc.perform(get("/url/shortner/50328aa4"))
	                .andDo(print())
	                .andExpect(status().isOk());
	        verify(urlService).getOrginalUrl("50328aa4");
	    }

	    @Test
	    void getShortUrlTest() throws Exception {
	    	Url url = new Url();
	    	url.setId(1);
	    	url.setOriginalUrl("https://www.google.com");
	    	url.setShortUrl("50328aa4");
	    	when(urlService.generateShortUrl("https://www.google.com")).thenReturn(url);
	    	 mockMvc.perform(post("/url/shortner/")
	                 .contentType(MediaType.APPLICATION_JSON)
	                 .content("https://www.google.com"))
             .andDo(print())
             .andExpect(status().isOk());
	    	 verify(urlService).generateShortUrl("https://www.google.com");	        
	    }
	    
	    @Test
	    void urlMustBeValid() {
	        String url = "https://www.google.com";	        
	        assertTrue(isUrlValid(url));
	    }
}
