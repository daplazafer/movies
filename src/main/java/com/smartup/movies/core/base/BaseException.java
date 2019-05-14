package com.smartup.movies.core.base;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 2082267081670636558L;

	private HttpStatus httpStatus;

	public BaseException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public BaseException setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		return this;
	}

}
