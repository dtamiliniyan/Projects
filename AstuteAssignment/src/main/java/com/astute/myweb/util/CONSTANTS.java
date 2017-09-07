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
	public static final String TEXT_OPERATION = "operation";
	public static final String EMPTY_STR = "";
	
	public static final String OPERATION_UPDATE = "update";
	public static final String OPERATION_DELETE = "delete";
	public static final String OPERATION_ADD = "add";
	
	public static final String ERROR_INVALID_USER = "User not yet logged in. Please login!";
	public static final String ERROR_TRANSLATION_NOT_FOUND = "Translation not found or Incorrect input. Please validate!";
	public static final String ERROR_INVALID_INPUT = "Invalid input value. Please re-validate!";
	public static final String ERROR_INCORRECT_USER = "Invalid user name & password. Please retry.";
	
	public static final String MESSAGE_LOGIN_SUCCESS = "Login Successful!";
	public static final String MESSAGE_TRANSLATION_DELETED = "Translation Deleted!";
	public static final String MESSAGE_TRANSLATION_UPDATED = "Translation Updated!";
	public static final String MESSAGE_TRANSLATION_ADDED = "New translation added!";
	
	//TODO: URI need to be changed to auto-generated absolute path.
	public static final String URI_POST_TRANSLATION = "http://localhost:8080/translations?id={id}";
	public static final String URI_PUT_TRANSLATION = "http://localhost:8080/translations/{id}?key={key}&value={value}&operation={operation}";
	public static final String URI_DELETE_TRANSLATION = "http://localhost:8080/translations/{id}";
	public static final String URI_GET_TRANSLATION_WITH_PARAM = "http://localhost:8080/translations/{id}?key={key}&value={value}";
	public static final String URI_GET_TRANSLATION = "http://localhost:8080/translations/{id}";


}
