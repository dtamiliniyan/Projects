package com.astute.myweb.util;

/**
 * @author Tamiliniyan
 * 
 * Constant file to have static final string values.
 */
public class CONSTANTS {

	public static final String VIEW_WELCOME = "welcome";
	public static final String VIEW_TRANSLATION_ADMIN = "translation-admin";
	
	public static final String TEXT_ID = "id";
	public static final String TEXT_USER = "user";
	public static final String TEXT_KEY = "key";
	public static final String TEXT_VALUE = "value";
	public static final String EMPTY_STR = "";
	
	public static final String ERROR_INVALID_USER = "User not yet logged in. Please login!";
	public static final String ERROR_TRANSLATION_NOT_FOUND = "Translation not found! Please add by Admin - create screen";
	public static final String ERROR_INVALID_INPUT = "Invalid or No input value. Please revalidate!";
	public static final String ERROR_INCORRECT_USER = "Invalid user name & password. Please retry.";
	
	public static final String MESSAGE_LOGIN_SUCCESS = "Login Successful!";
	public static final String MESSAGE_TRANSLATION_DELETED = "Translation Deleted!";
	public static final String MESSAGE_TRANSLATION_UPDATED = "Translation Updated!";
	public static final String MESSAGE_TRANSLATION_ADDED = "New translation added!";
	
	//TODO: URI need to be changed to auto-generated absolute path.
	public static final String URI_POST_TRANSLATION = "http://localhost:8080/translations?id={id}";
	public static final String URI_PUT_TRANSLATION = "http://localhost:8080/translations/{id}?key={key}&value={value}";
	public static final String URI_DELETE_TRANSLATION = "http://localhost:8080/translations/{id}";
	public static final String URI_GET_TRANSLATION_WITH_PARAM = "http://localhost:8080/translations/{id}?key={key}&value={value}";
	public static final String URI_GET_TRANSLATION = "http://localhost:8080/translations/{id}";

}
