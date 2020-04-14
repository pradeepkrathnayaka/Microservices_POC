package com.cg.fm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends RuntimeException {

	private static final long serialVersionUID = -6798154278095441848L;

	public InternalErrorException(String s) {
		super(s);
	}

	public InternalErrorException() {
		super();
	}

	public InternalErrorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InternalErrorException(Throwable arg0) {
		super(arg0);
	}

}
