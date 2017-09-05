/**
 * 
 */
package com.astute.myweb.controller.rest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.astute.myweb.util.CONSTANTS;

/**
 * @author Tamiliniyan
 *
 */
public class TranslationsRestControllerTest {

//	String json = null;
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//
//	}
//
//	/**
//	 * Test method for {@link com.astute.myweb.controller.rest.TranslationsRestController#getTranslation(java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testGetTranslation() {
//	    RestTemplate restTemplate = new RestTemplate();
//	    HttpHeaders headers = new HttpHeaders();
//	    	
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//		Map<String, String> params = new HashMap<String, String>();
//		params.put(CONSTANTS.TEXT_ID, "111");
//
//	    HttpEntity<String> entity = new HttpEntity<String>(json,headers);
//	    restTemplate.postForObject(CONSTANTS.URI_POST_TRANSLATION, entity, ResponseEntity.class, params);
//		assertTrue(true);
//	}
//
//	/**
//	 * Test method for {@link com.astute.myweb.controller.rest.TranslationsRestController#getTranslations()}.
//	 */
//	@Test
//	public void testGetTranslations() {
//	    RestTemplate restTemplate = new RestTemplate();
//	    HttpHeaders headers = new HttpHeaders();
//	    	
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//	    HttpEntity<String> entity = new HttpEntity<String>(json,headers);
//	    restTemplate.postForObject(CONSTANTS.URI_POST_TRANSLATION, entity, ResponseEntity.class);
//		assertTrue(true);
//	}
//
//	/**
//	 * Test method for {@link com.astute.myweb.controller.rest.TranslationsRestController#addTranslation(java.util.Map, java.lang.String)}.
//	 */
//	@Test
//	public void testAddTranslation() {
//	    RestTemplate restTemplate = new RestTemplate();
//	    HttpHeaders headers = new HttpHeaders();
//	    
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//		Map<String, String> params = new HashMap<String, String>();
//		params.put(CONSTANTS.TEXT_ID, "111");
//
//	    HttpEntity<String> entity = new HttpEntity<String>(json,headers);
//	    restTemplate.postForObject(CONSTANTS.URI_POST_TRANSLATION, entity, ResponseEntity.class, params);
//	    assertTrue(true);
//	}
//
//	/**
//	 * Test method for {@link com.astute.myweb.controller.rest.TranslationsRestController#updateTranslation(java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testUpdateTranslation() {
//		
//		
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//	    HttpEntity<String> entity = new HttpEntity<String>(headers);
//	    
//		Map<String, String> params = new HashMap<String, String>();
//		params.put(CONSTANTS.TEXT_ID, "111");
//		params.put(CONSTANTS.TEXT_KEY, "login.user");
//		params.put(CONSTANTS.TEXT_VALUE, "USER NAME");
//
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.put(CONSTANTS.URI_PUT_TRANSLATION, entity, params);
//		assertTrue(true);
//	}
//
//	/**
//	 * Test method for {@link com.astute.myweb.controller.rest.TranslationsRestController#deleteTranslation(java.lang.String)}.
//	 */
//	@Test
//	public void testDeleteTranslation() {
//	
//	    Map<String, String> params = new HashMap<String, String>();
//	    params.put(CONSTANTS.TEXT_ID, "111");
//	    
//	    RestTemplate restTemplate = new RestTemplate();
//	    restTemplate.delete (CONSTANTS.URI_DELETE_TRANSLATION, params);
//	    assertTrue(true);
//	}

}
