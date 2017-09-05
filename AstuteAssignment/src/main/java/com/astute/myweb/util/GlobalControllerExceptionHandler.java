package com.astute.myweb.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.astute.myweb.pojo.TranslationForm;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

/**
 * @author Tamiliniyan
 * 
 *	Global Exception handler class.
 */
@ControllerAdvice
class GlobalControllerExceptionHandler {
	  
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleNotFound(Model model) {
    	TranslationForm form = new TranslationForm();
    	form.setError(CONSTANTS.ERROR_INVALID_USER);
    	model.addAttribute("translationForm", form);
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidInputDataException.class)
    public String handleInvalidInputData(Model model, InvalidInputDataException exception) {
    	TranslationForm form = new TranslationForm();
    	form.setMessage(exception.getMessage());
    	model.addAttribute("translationForm", form);
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public String handleBadRequest(Model model, HttpClientErrorException exception) {
    	TranslationForm form = new TranslationForm();
    	form.setError(CONSTANTS.ERROR_INVALID_INPUT);
    	model.addAttribute("translationForm", form);
		return CONSTANTS.VIEW_TRANSLATION_ADMIN;
    }
}