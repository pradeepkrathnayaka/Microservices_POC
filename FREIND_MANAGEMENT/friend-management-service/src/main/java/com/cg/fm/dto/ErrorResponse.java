package com.cg.fm.dto;

import java.time.LocalDateTime;

import com.cg.fm.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Throwables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Predeep
 *
 */
@Getter
@Setter
@ToString
public class ErrorResponse extends EnvelopeResponse {
	public String stacktrace;
	public String exception;
	public String url;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
	
	public ErrorResponse(String url, Exception exception) {
		super();
		this.url = url;
		this.exception = exception.getLocalizedMessage();
		this.msg = exception.getLocalizedMessage();
		this.stacktrace = Throwables.getStackTraceAsString(exception);
		this.timestamp = LocalDateTime.now();
		if (exception instanceof ApiException) {
			this.code = ((ApiException) exception).getErrorCode();
		} else {
			this.code = ResponseCode.CODE_UNDEFINED;
		}
	}
}
