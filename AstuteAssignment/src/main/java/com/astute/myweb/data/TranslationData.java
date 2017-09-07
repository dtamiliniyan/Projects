/**
 * 
 */
package com.astute.myweb.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
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

	private static volatile Map<String, Map<Object, Object>> translations = new ConcurrentHashMap<String, Map<Object, Object>>();

	/**
	 * Add new translation to in memory storage
	 * @param translation
	 * @param user
	 * @return user
	 */
	public String addTranslation(Map<Object, Object> translation, String user) {
		translations.put(user, translation);
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
	 * maintenance of specific element in Translation.
	 * this method performs all three operations.
	 * @param map
	 * @param level
	 * @param resultMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean elementMaintenance(Map<Object, Object> translation, String operation, String key, String value,
			List<String> levels, int count) {
		boolean maintenanceDone = false;

		if (translation != null) {

			Set<Entry<Object, Object>> set = translation.entrySet();
			for (Map.Entry<Object, Object> entry : set) {
				if (levels == null) {
					levels = new ArrayList<String>();
					levels.add(CONSTANTS.EMPTY_STR);
				}
				if (entry.getValue() instanceof Map) {
					levels.add(entry.getKey().toString() + ".");
					maintenanceDone = elementMaintenance((Map<Object, Object>) entry.getValue(), operation, key, value, levels, ++count);
					levels.remove(--count);
				} else {
					String parent = CONSTANTS.EMPTY_STR;
					// parent = levels.stream().map(level -> parent +
					// level).collect(Collectors.joining());
					for (String level : levels) {
						parent = parent + level;
					}

					if (CONSTANTS.OPERATION_UPDATE.equals(operation)) {
						if (key.equals(parent + entry.getKey().toString())) {
							entry.setValue(value);
							maintenanceDone = true;
						}
					} else if (CONSTANTS.OPERATION_ADD.equals(operation)) {
						//Identify key till last dot and compare with parent to identify exact sub-element to add new field.
						int position = key.lastIndexOf(".");
						String keyHeader = key.substring(0, position+1);
						String endKey = key.substring(position+1, key.length());
						if (keyHeader.equals(parent)) {
							translation.put(endKey, value);
							maintenanceDone = true;
						}
					} else if (CONSTANTS.OPERATION_DELETE.equals(operation)) {
						if (key.equals(parent + entry.getKey().toString())) {
							translation.remove(entry.getKey());
							maintenanceDone = true;
						}
					}
				}
				if (maintenanceDone) {
					break;
				}
			}
		}
		return maintenanceDone;
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
		List<String> list = new ArrayList<String>();
		list.add(CONSTANTS.EMPTY_STR);
		
		if (translation == null) {
			throw new InvalidInputDataException(CONSTANTS.ERROR_TRANSLATION_NOT_FOUND);
		}

		keyValues = convertMapToKeyValues(translation, list, 1, keyValues);
		return keyValues.get(key);
	}

	/**
	 * 
	 * Get translation element by taking value as input
	 * @param user
	 * @param value
	 * @return
	 */
	public List<String> getTranslationElementByValue(String user, String value) {
		Map<String, String> keyValues = new HashMap<String, String>();

		List<String> matchingValues = new ArrayList<String>();
		Map<Object, Object> translation = translations.get(user);
		
		if (translation == null) {
			throw new InvalidInputDataException(CONSTANTS.ERROR_TRANSLATION_NOT_FOUND);
		}
		
		keyValues = convertMapToKeyValues(translation, null, 1, keyValues);
		
		Set<Entry<String, String>> entries = keyValues.entrySet();
		for (Map.Entry<String, String> entry : entries) {
			if(value.equals(keyValues.get(entry.getKey()))) {
				matchingValues.add(entry.getKey());
			}
		}
		return matchingValues;
	}
	
	
	/**
	 * Method to convert Transactions into key value pairs.
	 * @param map
	 * @param level
	 * @param count
	 * @param resultMap
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> convertMapToKeyValues(Map<Object, Object> map, List<String> levels, int count, Map<String, String> resultMap) {
		if (map != null) {
			if(levels == null) {
				levels = new ArrayList<String>();
				levels.add(CONSTANTS.EMPTY_STR);
			}
			Set<Entry<Object, Object>> set = map.entrySet();
			for (Map.Entry<Object, Object> entry : set) {
				if (entry.getValue() instanceof Map) {
					levels.add(entry.getKey().toString() + ".");
					convertMapToKeyValues((Map<Object, Object>) entry.getValue(), levels, ++count, resultMap);
					levels.remove(--count);
				} else {
					String parent = CONSTANTS.EMPTY_STR;
					for (String level : levels) {
						parent = parent + level;
					}
					resultMap.put((parent + entry.getKey().toString()), entry.getValue().toString());
				}
			}
		}
		return resultMap;
	}

	/**
	 * Element level maintenance operations.
	 * Performs add, update delete element.
	 * @param user
	 * @param operation
	 * @param key
	 * @param value
	 */
	public void elementMaintenance(String user, String operation, String key, String value) {
		Map<Object, Object> translation = translations.get(user);
		boolean success = elementMaintenance(translation, operation, key, value, null, 1);
		if (!success) {
			throw new InvalidInputDataException(key);
		}
	}
}
