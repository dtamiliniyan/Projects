/**
 * 
 */
package com.astute.myweb.controller.rest;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.astute.myweb.service.TranslationService;
import com.astute.myweb.util.CONSTANTS;

/**
 * @author Tamiliniyan
 *
 * RESTful services for Translation entry creation and maintenance.
 * This is independent Back-end services which can be invoked by any consumers.
 * Translation REST currently invoked by MVC controller which acts as client program for this service.
 * Basically this performs all base REST processes like GET, POST, PUT and DELETE
 */
@RestController
@RequestMapping(value = "/translations")
public class TranslationsRestController implements Translations {

	@Autowired
	private TranslationService translationService;

	/* (non-Javadoc)
	 * @see com.astute.myweb.controller.rest.Translations#getTranslation(java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * RESTful service GET method.
	 * Search method to search translation by sending id, key, value.
	 * It perform individual search on id or key or value and return values.
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getTranslation(@PathVariable(value = CONSTANTS.TEXT_ID, required = true) String user,
			@RequestParam(value = CONSTANTS.TEXT_KEY, required = false) String searchBy,
			@RequestParam(value = CONSTANTS.TEXT_VALUE, required = false) String value) {

		if (CONSTANTS.TEXT_KEY.equals(searchBy)) {
			return translationService.getTranslationEntryByKey(user, value);
		} else if (CONSTANTS.TEXT_VALUE.equals(searchBy)) {
			List<String> matches = translationService.getTranslationEntryByValue(user, value);
			String result = matches.stream().map(match -> match+" ").collect(Collectors.joining());
			return result;
		}
		return translationService.getTranslation(value);
	}

	/* (non-Javadoc)
	 * @see com.astute.myweb.controller.rest.Translations#getTranslations()\
	 * 
	 * GET all translations.
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public Object getTranslations() {
		return translationService.getTranslations();
	}

	/* (non-Javadoc)
	 * @see com.astute.myweb.controller.rest.Translations#addTranslation(java.util.Map, java.lang.String)
	 * 
	 * RESTful service POST method.
	 * Creates new translation object.
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addTranslation(@Valid @RequestBody Map<Object, Object> translation,
			@RequestParam(value = CONSTANTS.TEXT_ID, required = false) String user) {
		String userId = translationService.addTranslation(translation, user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
		return ResponseEntity.created(location).build();
	}

	/* (non-Javadoc)
	 * @see com.astute.myweb.controller.rest.Translations#updateTranslation(java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * RESTful service PUT method.
	 * Updates existing translation element by sending operation type, key and value.
	 * Element level maintenance operations.
	 * Performs add, update delete element.
	 * 
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTranslation(@PathVariable(value = CONSTANTS.TEXT_ID, required = true) String user,
			@RequestParam(value = CONSTANTS.TEXT_OPERATION, required = true) String operation,
			@RequestParam(value = CONSTANTS.TEXT_KEY, required = true) String key,
			@RequestParam(value = CONSTANTS.TEXT_VALUE, required = true) String value) {
		try {
			translationService.elementMaintenance(user, operation, key, value);
			return ResponseEntity.ok().build();
		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	/* (non-Javadoc)
	 * @see com.astute.myweb.controller.rest.Translations#deleteTranslation(java.lang.String)
	 * RESTful service DELETE method.
	 * Delete existing translation element based on the user logged in.
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTranslation(@PathVariable(value = CONSTANTS.TEXT_ID, required = true) String user) {
		try {
			translationService.deleteTranslation(user);
			return ResponseEntity.noContent().build();
		} catch (Exception exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
