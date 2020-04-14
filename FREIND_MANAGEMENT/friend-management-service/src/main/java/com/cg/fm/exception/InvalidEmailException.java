package com.cg.fm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidEmailException extends ApiException {
	
	private static final long serialVersionUID = 1L;
	public static final String CODE = "902.1";

	public InvalidEmailException(String code) {
		super(code);
	}
	
	public InvalidEmailException(String code, String[] arr) {
		super(code, arr);
	}

	public InvalidEmailException() {
		super(CODE);
	}

	public InvalidEmailException(String code, String userOneEmail, String userTwoEmail) {
		super(CODE);
	}
}
