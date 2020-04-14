package com.cg.fm.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestApiException extends ApiException {
	private static final long serialVersionUID = 1L;
	public static final String CODE = "902";
	
	public InvalidRequestApiException(String code) {
        super(code);
    }

	public InvalidRequestApiException() {
		super(CODE);
	}

	public InvalidRequestApiException(Throwable cause) {
		super(CODE, cause);
	}

	public void bindTo(Errors errors, String field, Object... messageArgs) {
		String msg = getMessage();
		if (messageArgs.length > 0) {
			msg = MessageFormat.format(msg, messageArgs);
			errors.rejectValue(field, getErrorCode(), messageArgs, msg);
		} else {
			errors.rejectValue(field, getErrorCode(), msg);
		}
	}
}