package com.astute.myweb.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tamiliniyan
 * Customer Exception to handle Invalid user input.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInputDataException(String userId) {
		super("Invalid input value - '" + userId + "'.");
	}
}