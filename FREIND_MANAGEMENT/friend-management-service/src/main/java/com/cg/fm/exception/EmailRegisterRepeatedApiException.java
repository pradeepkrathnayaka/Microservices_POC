package com.cg.fm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailRegisterRepeatedApiException extends ApiException {
	private static final long serialVersionUID = 1L;
	
	public static final String CODE = "903";

	public EmailRegisterRepeatedApiException() {
		super(CODE);
	}

	public EmailRegisterRepeatedApiException(String userOneEmail, String userTwoEmail) {
		super(CODE);
	}

}