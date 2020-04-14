package com.cg.fm.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.fm.dto.ErrorResponse;
import com.cg.fm.exception.EmailNotRegisteredApiException;
import com.cg.fm.exception.InvalidEmailException;
import com.cg.fm.exception.InvalidRequestApiException;
import com.cg.fm.exception.InvalidUserException;
import com.cg.fm.exception.NotFoundException;

@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler{	
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		ErrorResponse errorMessage = new ErrorResponse(request.getContextPath(), ex);
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
    
	@ExceptionHandler({ InvalidRequestApiException.class })
	public ResponseEntity<Object> handleInvalidRequesException(HttpServletRequest req, HttpServletResponse res,
			InvalidRequestApiException e) {
		ErrorResponse errorResp = new ErrorResponse(req.getRequestURI(), e);
		return error(HttpStatus.valueOf(res.getStatus()), errorResp, e);
	}
	
	@ExceptionHandler({ InvalidUserException.class })
	public ResponseEntity<Object> handleInvalidUserException(HttpServletRequest req, HttpServletResponse res,
			InvalidUserException e) {
		ErrorResponse errorMessage = new ErrorResponse(req.getRequestURI(), e);
		errorMessage.setException(e.getMessage());
		return error(HttpStatus.valueOf(res.getStatus()), errorMessage, e);
	}
	
	@ExceptionHandler({ EmailNotRegisteredApiException.class })
	public ResponseEntity<Object> handleInvalidUserException(HttpServletRequest req, HttpServletResponse res,
			EmailNotRegisteredApiException e) {
		ErrorResponse errorMessage = new ErrorResponse(req.getRequestURI(), e);
		errorMessage.setException(e.getMessage());
		return error(HttpStatus.valueOf(res.getStatus()), errorMessage, e);
	}
	
	@ExceptionHandler({ InvalidEmailException.class })
	public ResponseEntity<Object> handleInvalidEmailException(HttpServletRequest req, HttpServletResponse res,
			InvalidEmailException e) {
		ErrorResponse errorMessage = new ErrorResponse(req.getRequestURI(), e);
		errorMessage.setException(e.getMessage());
		return error(HttpStatus.valueOf(res.getStatus()), errorMessage, e);
	}	

	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(HttpServletRequest req, HttpServletResponse res,
			NotFoundException e) {
		ErrorResponse errorMessage = new ErrorResponse(req.getRequestURI(), e);
		errorMessage.setException(e.getMessage());
		return error(HttpStatus.valueOf(res.getStatus()), errorMessage, e);
	}

	private ResponseEntity<Object> error(HttpStatus status, ErrorResponse errorMessage, Exception e) {
		return ResponseEntity.status(status).body(errorMessage);
	}

}
