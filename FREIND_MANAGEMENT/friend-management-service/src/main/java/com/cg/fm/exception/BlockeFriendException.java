package com.cg.fm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BlockeFriendException extends ApiException {

	private static final long serialVersionUID = 1L;
	public static final String CODE = "908";
	
	public BlockeFriendException(String code) {
		super(code);
	}

	public BlockeFriendException() {
		super(CODE);
	}
}
