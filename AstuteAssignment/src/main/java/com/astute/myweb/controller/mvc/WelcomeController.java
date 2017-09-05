package com.astute.myweb.controller.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.astute.myweb.pojo.TranslationForm;
import com.astute.myweb.util.CONSTANTS;

/**
 * @author Tamiliniyan
 *
 * Welcome MVC controller. 
 * Invoked by welcome/home screen link to load the Hoome screen.
 * This controller will invoke REST controller class retrieve translations that require.
 */
@Controller
public class WelcomeController {

	/**
	 * Loads Welcome screen by invoking Translation rest service.
	 * @param model
	 * @param translationForm
	 * @param session
	 * @return VIEW_WELCOME
	 */
	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model, @ModelAttribute TranslationForm translationForm, HttpSession session ) {
	
		if(session.getAttribute(CONSTANTS.TEXT_USER) == null) {
		    translationForm.setError(CONSTANTS.ERROR_INVALID_USER);
			return CONSTANTS.VIEW_TRANSLATION_ADMIN;
		}

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put(CONSTANTS.TEXT_ID, session.getAttribute(CONSTANTS.TEXT_USER).toString());

		String result = restTemplate.getForObject(CONSTANTS.URI_GET_TRANSLATION, String.class, params);
	
		if(result!=null) {
			translationForm.setTranslationJson(result);
		} else {
			translationForm.setError(CONSTANTS.ERROR_TRANSLATION_NOT_FOUND);
		}
		return CONSTANTS.VIEW_WELCOME;
	}
}