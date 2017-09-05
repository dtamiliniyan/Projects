/**
 * 
 */
package com.astute.myweb.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astute.myweb.data.TranslationData;

/**
 * 
 * Business Service implementation
 * 
 * @author Tamiliniyan
 */
@Service
public class TranslationService {
 
	@Autowired
	private TranslationData translationData;
	
	/**
	 * Get translation specific to an user
	 * @param user
	 * @return
	 */
	public Map<Object, Object> getTranslation(String user) {
		return translationData.getTranslation(user);
	}

	/**
	 * Add new translation
	 * 
	 * @param translation
	 * @param user
	 * @return
	 */
	public String addTranslation(Map<Object, Object> translation, String user) {
		return translationData.addTranslation(translation, user);
	}

	/**
	 * Update translation specific to the user.
	 * @param user
	 * @param translation
	 */
	public void updateTranslation(String user, Map<Object, Object> translation) {
		translationData.updateTranslation(user, translation);
	}

	/**
	 * Delete translation by user
	 * @param user
	 */
	public void deleteTranslation(String user) {
		translationData.deleteTranslation(user);
	}

	/**
	 * Get all translations.
	 * @return map
	 */
	public Map<String, Map<Object, Object>> getTranslations() {
		return translationData.getTranslations();
	}

	/**
	 * Update translation element.
	 * This method retrieve key and value to update specific user translation element.
	 * @param user
	 * @param key
	 * @param value
	 */
	public void updateTranslation(String user, String key, String value) {
		translationData.updateTranslation(user, key, value);
	}

	/**
	 * Get translation element by taking key as input
	 * @param user
	 * @param searchBy
	 * @return
	 */
	public String getTranslationEntryByKey(String user, String searchBy) {
		return translationData.getTranslationElementByKey(user, searchBy);
	}
	
	/**
	 * Get translation element by taking value as input
	 * @param user
	 * @param value
	 * @return
	 */
	public String getTranslationEntryByValue(String user, String value) {
		return translationData.getTranslationElementByValue(user, value);
	}
}
