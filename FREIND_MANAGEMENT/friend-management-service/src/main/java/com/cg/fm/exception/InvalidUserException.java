package com.cg.fm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserException extends ApiException {

	private static final long serialVersionUID = 1L;
	public static final String CODE = "903";

	public InvalidUserException(String code) {
		super(code);
	}

	public InvalidUserException() {
		super(CODE);
	}

	public InvalidUserException(String userOneEmail, String userTwoEmail) {
		super(CODE);
	}

}
