package com.astute.myweb.pojo;

/**
 * @author Tamiliniyan
 * 
 * Translation form to transfer values between UI vs. Controller.
 */
public class TranslationForm {

	private String isKeySearch;
	private String searchBy;
	private String key;
	private String inputValue;
	private String translationJson;
	private String message;
	private String error;
	private String user;

	public String getIsKeySearch() {
		return isKeySearch;
	}

	public void setIsKeySearch(String isKeySearch) {
		this.isKeySearch = isKeySearch;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getTranslationJson() {
		return translationJson;
	}

	public void setTranslationJson(String translationJson) {
		this.translationJson = translationJson;
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
