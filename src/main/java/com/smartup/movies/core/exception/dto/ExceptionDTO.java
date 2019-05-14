package com.smartup.movies.core.exception.dto;

import java.io.Serializable;
import java.util.Date;

public class ExceptionDTO implements Serializable{
	
	private static final long serialVersionUID = 1010091763069900240L;
	
	private Date timestamp;
	private String message;

	public String getMessage() {
		return message;
	}

	public ExceptionDTO setMessage(String message) {
		this.message = message;
		return this;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public ExceptionDTO setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
		return this;
	}

}
