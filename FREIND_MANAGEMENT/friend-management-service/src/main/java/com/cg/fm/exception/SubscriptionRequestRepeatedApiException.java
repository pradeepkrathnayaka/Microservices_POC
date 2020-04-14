package com.cg.fm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SubscriptionRequestRepeatedApiException extends ApiException {

	private static final long serialVersionUID = 1L;
	public static final String CODE = "906";

    public SubscriptionRequestRepeatedApiException() {
        super(CODE);
    }

}
