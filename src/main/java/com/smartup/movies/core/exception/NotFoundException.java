package com.smartup.movies.core.exception;

import org.springframework.http.HttpStatus;

import com.smartup.movies.core.base.BaseException;

public class NotFoundException extends BaseException {
	
	private static final long serialVersionUID = -8482793380466403217L;

	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
	
}
