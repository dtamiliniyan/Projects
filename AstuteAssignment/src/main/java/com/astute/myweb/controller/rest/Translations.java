package com.astute.myweb.controller.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;

/**
 * @author Tamiliniyan
 *
 * Translations REST Controller interface.
 */
public interface Translations {

	Object getTranslation(String user, String searchBy, String value);

	Object getTranslations();

	ResponseEntity<String> addTranslation(Map<Object, Object> translation, String user);

	ResponseEntity<?> updateTranslation(String user, String operation, String key, String value);

	ResponseEntity<?> deleteTranslation(String user);

}
