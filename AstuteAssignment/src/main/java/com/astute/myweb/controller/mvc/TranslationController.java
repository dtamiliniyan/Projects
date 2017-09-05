package com.astute.myweb.controller.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.astute.myweb.pojo.TranslationForm;
import com.astute.myweb.util.CONSTANTS;

/**
 * @author Tamiliniyan
 *
 * This is translation MVC controller. 
 * It acts as end client for Translation restful services layer.
 * Performs create, update, search and delete translation.
 */
@Controller
@RequestMapping("/adminscreen")
public class TranslationController {

	/**
	 * initial load method to launch translation admin screen
	 * @param model
	 * @return VIEW_TRANSLATION_ADMIN
	 */
	@GetMapping
	public String load(Model model) {
		model.addAttribute("translationForm", new TranslationForm());
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
	}

	
	/**
	 * Create translation by invoking REST services.
	 * @param translationForm
	 * @param session
	 * @return VIEW_TRANSLATION_ADMIN
	 */
	@PostMapping("/create")
	public String createTranslation(@ModelAttribute TranslationForm translationForm, HttpSession session) {
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    
		if(!validUser(translationForm, session)) {
			return CONSTANTS.VIEW_TRANSLATION_ADMIN;
		}
		
	    headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, String> params = new HashMap<String, String>();
		params.put(CONSTANTS.TEXT_ID, session.getAttribute(CONSTANTS.TEXT_USER).toString());

	    HttpEntity<String> entity = new HttpEntity<String>(translationForm.getTranslationJson(),headers);
	    restTemplate.postForObject(CONSTANTS.URI_POST_TRANSLATION, entity, ResponseEntity.class, params);
	    translationForm.setMessage(CONSTANTS.MESSAGE_TRANSLATION_ADDED);
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
	}
	
	/**
	 * Search method to search translation by sending id, key, value.
	 * It perform individual search on id or key or value and return values.
	 * @param translationForm
	 * @param session
	 * @return VIEW_TRANSLATION_ADMIN
	 */
	@GetMapping("/search")
	public String searchTranslation(@ModelAttribute TranslationForm translationForm, HttpSession session) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		
		if(!validUser(translationForm, session)) {
			return CONSTANTS.VIEW_TRANSLATION_ADMIN;
		}
		
		params.put(CONSTANTS.TEXT_ID, session.getAttribute(CONSTANTS.TEXT_USER).toString());
		params.put(CONSTANTS.TEXT_KEY, translationForm.getSearchBy());
		params.put(CONSTANTS.TEXT_VALUE, translationForm.getInputValue());

		String result = restTemplate.getForObject(CONSTANTS.URI_GET_TRANSLATION_WITH_PARAM, String.class, params);
		if (result != null) {
			translationForm.setMessage(result);
		} else {
			translationForm.setError(CONSTANTS.ERROR_TRANSLATION_NOT_FOUND);
		}
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
	}
	
	/**
	 * update translation entry using key.
	 * @param translationForm
	 * @param session
	 * @return VIEW_TRANSLATION_ADMIN
	 */
	@PostMapping("/update")
	public String updateTranslationEntry(@ModelAttribute TranslationForm translationForm, HttpSession session) {

		if(!validUser(translationForm, session)) {
			return CONSTANTS.VIEW_TRANSLATION_ADMIN;
		}
		
		if (StringUtils.isEmpty(translationForm.getKey()) 
				|| StringUtils.isEmpty(translationForm.getInputValue())) {
		    translationForm.setError(CONSTANTS.ERROR_INVALID_INPUT);
			return CONSTANTS.VIEW_TRANSLATION_ADMIN;
		}
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    
		Map<String, String> params = new HashMap<String, String>();
		params.put(CONSTANTS.TEXT_ID, session.getAttribute(CONSTANTS.TEXT_USER).toString());
		params.put(CONSTANTS.TEXT_KEY, translationForm.getKey());
		params.put(CONSTANTS.TEXT_VALUE, translationForm.getInputValue());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(CONSTANTS.URI_PUT_TRANSLATION, entity, params);
	    
	    translationForm.setMessage(CONSTANTS.MESSAGE_TRANSLATION_UPDATED);
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
	}
	
	/**
	 * delete translation of specific user by invoking restful service.
	 * @param translationForm
	 * @param session
	 * @return
	 */
	@PostMapping("/delete")
	public String deleteTranslation(@ModelAttribute TranslationForm translationForm, HttpSession session) {

		if(!validUser(translationForm, session)) {
			return CONSTANTS.VIEW_TRANSLATION_ADMIN;
		}
	
	    Map<String, String> params = new HashMap<String, String>();
	    params.put(CONSTANTS.TEXT_ID, session.getAttribute(CONSTANTS.TEXT_USER).toString());
	    
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete (CONSTANTS.URI_DELETE_TRANSLATION, params);
	    translationForm.setMessage(CONSTANTS.MESSAGE_TRANSLATION_DELETED);
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
	}
	
	/**
	 * User session validation
	 * @param translationForm
	 * @param session
	 * @return
	 */
	private boolean validUser(TranslationForm translationForm, HttpSession session) {
		if(session.getAttribute(CONSTANTS.TEXT_USER) == null) {
		    translationForm.setError(CONSTANTS.ERROR_INVALID_USER);
			return false;
		}
		return true;
	}
}
