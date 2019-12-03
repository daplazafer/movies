package com.dpf.movies.core.exception;

import com.dpf.movies.core.base.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
	
}
