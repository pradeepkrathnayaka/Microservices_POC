package com.cg.fm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnreadableRequestApiException extends ApiException {
	private static final long serialVersionUID = 1L;
	public static final String CODE = "901";

	public UnreadableRequestApiException() {
		super(CODE);
	}

	public UnreadableRequestApiException(Throwable cause) {
		super(CODE, cause);
	}
}