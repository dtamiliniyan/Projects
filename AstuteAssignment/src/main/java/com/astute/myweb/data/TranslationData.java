/**
 * 
 */
package com.astute.myweb.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.astute.myweb.util.CONSTANTS;
import com.astute.myweb.util.InvalidInputDataException;
import com.astute.myweb.util.UserNotFoundException;

/**
 * @author Tamiliniyan
 * 
 * Translation Data processing.
 * This class stores all translations in static Map for the purpose of translation handling.
 * All object will get wiped while application restarted since its in memory storage.
 * 
 * TODO: Implement database or S3 services to store translations to permanent memory.
 */
@Component
public class TranslationData {

	private static volatile Map<String, Map<Object, Object>> translations = new HashMap<String, Map<Object, Object>>();

	/**
	 * Add new translation to in memory storage
	 * @param translation
	 * @param user
	 * @return user
	 */
	public String addTranslation(Map<Object, Object> translation, String user) {
		translations.put(user, translation);
		System.out.println(translation);
		return user;
	}

	/**
	 * Get translation specific to an user from memory
	 * @param user
	 * @return Map
	 */
	public Map<Object, Object> getTranslation(String user) {
		return translations.get(user);
	}

	/**
	 * Update translation specific to the user.
	 * @param user
	 * @param translation
	 */
	public void updateTranslation(String user, Map<Object, Object> translation) {
		translations.put(user, translation);
	}

	/**
	 * delete translation by user
	 * @param user
	 */
	public void deleteTranslation(String user) {
		if (translations.get(user) == null) {
			throw new UserNotFoundException(user);
		}
		translations.remove(user);
	}

	/**
	 * Get all translations.
	 * @return
	 */
	public Map<String, Map<Object, Object>> getTranslations() {
		return translations;
	}

	/**
	 * Update translation element.
	 * This method retrieve key and value to update specific user translation element.
	 * @param user
	 * @param key
	 * @param value
	 */
	public void updateTranslation(String user, String key, String value) {
		Map<Object, Object> translation = translations.get(user);
		boolean success = updateTranslationElement(translation, "", key, value);
		if (!success) {
			throw new InvalidInputDataException(key);
		}
	}


	/**
	 * @param map
	 * @param level
	 * @param resultMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateTranslationElement(Map<Object, Object> map, String level, String key, String valueToUpdate) {
		if (map != null) {
			Set<Entry<Object, Object>> set = map.entrySet();
			for (Map.Entry<Object, Object> entry : set) {
				if (entry.getValue() instanceof Map) {
					level = level + entry.getKey().toString() + ".";
					updateTranslationElement((Map<Object, Object>) entry.getValue(), level, key, valueToUpdate);
					level = "";
				} else {
					if (key.equals((level + entry.getKey().toString()))) {
						entry.setValue(valueToUpdate);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Get translation element by taking key as input
	 * @param user
	 * @param key
	 * @return
	 */
	public String getTranslationElementByKey(String user, String key) {
		Map<String, String> keyValues = new HashMap<String, String>();
		Map<Object, Object> translation = translations.get(user);
		if (translation == null) {
			throw new InvalidInputDataException(CONSTANTS.ERROR_TRANSLATION_NOT_FOUND);
		}

		keyValues = convertMapToKeyValues(translation, CONSTANTS.EMPTY_STR, keyValues);
		return keyValues.get(key);
	}

	/**
	 * 
	 * Get translation element by taking value as input
	 * @param user
	 * @param value
	 * @return
	 */
	public String getTranslationElementByValue(String user, String value) {
		Map<String, String> keyValues = new HashMap<String, String>();
		Map<Object, Object> translation = translations.get(user);
		if (translation == null) {
			throw new InvalidInputDataException(CONSTANTS.ERROR_TRANSLATION_NOT_FOUND);
		}
		keyValues = convertMapToKeyValues(translation, CONSTANTS.EMPTY_STR, keyValues);
		Set<Entry<String, String>> set = keyValues.entrySet();
		for (Map.Entry<String, String> entry : set) {
			if(value.equals(keyValues.get(entry.getKey()))) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	/**
	 * Method to convert Transactions into key value pairs.
	 * @param map
	 * @param level
	 * @param resultMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> convertMapToKeyValues(Map<Object, Object> map, String level, Map<String, String> resultMap) {
		if (map != null) {
			Set<Entry<Object, Object>> set = map.entrySet();
			for (Map.Entry<Object, Object> entry : set) {
				if (entry.getValue() instanceof Map) {
					level = level + entry.getKey().toString() + ".";
					convertMapToKeyValues((Map<Object, Object>) entry.getValue(), level, resultMap);
					level = CONSTANTS.EMPTY_STR;
				} else {
					resultMap.put((level + entry.getKey().toString()), entry.getValue().toString());
				}
			}
		}
		return resultMap;
	}
}
