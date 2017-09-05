package com.astute.myweb.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tamiliniyan
 * Customer Exception to handle Invalid user.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String userId) {
		super("Could not find user '" + userId + "'.");
	}
}