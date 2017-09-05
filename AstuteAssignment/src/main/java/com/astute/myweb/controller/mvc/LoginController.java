/**
 * 
 */
package com.astute.myweb.controller.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.astute.myweb.pojo.TranslationForm;
import com.astute.myweb.util.CONSTANTS;

/**
 * 
 * This is login Spring MVC controller.
 * Play a role of retriving user id and store to session.
 * @author Tamiliniyan
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@PostMapping
	public String login(@ModelAttribute TranslationForm translationForm, HttpSession session) {
		
		if(StringUtils.isEmpty(translationForm.getUser())) {
		    translationForm.setError(CONSTANTS.ERROR_INCORRECT_USER);
			return CONSTANTS.VIEW_TRANSLATION_ADMIN;
		}
		
		session.setAttribute(CONSTANTS.TEXT_USER, translationForm.getUser());
		translationForm.setMessage(CONSTANTS.MESSAGE_LOGIN_SUCCESS);
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
	}
}
