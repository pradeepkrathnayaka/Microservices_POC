package com.cg.fm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.fm.dto.ResponseCode;

@ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS)
public class TooManyRequestException extends RuntimeException {

    private static final long serialVersionUID = -6798154278095441848L;

    private String code;

    /**
     * legacy support, new APIs should not call this. Instead, new APIs should provide return code
     */
    public TooManyRequestException(String msg) {
        super(msg);
        this.code = ResponseCode.CODE_UNDEFINED;
    }

    public TooManyRequestException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public TooManyRequestException(String msg, String code, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
