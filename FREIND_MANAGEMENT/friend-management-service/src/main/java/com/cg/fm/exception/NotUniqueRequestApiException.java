package com.cg.fm.exception;

import lombok.Getter;

public class NotUniqueRequestApiException extends InvalidRequestApiException {

    private static final long serialVersionUID = -3337491517713872679L;
    public static final String CODE = "902.2";
    @Getter
    private final String errorCode = CODE;
}
