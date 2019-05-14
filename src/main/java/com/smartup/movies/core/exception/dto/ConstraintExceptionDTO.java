package com.smartup.movies.core.exception.dto;

import java.io.Serializable;
import java.util.Date;

public class ConstraintExceptionDTO implements Serializable {

	private static final long serialVersionUID = -1313222057553137329L;

	private Date timestamp;
	private String message;
	private String field;
	private String constraint;

	public String getField() {
		return field;
	}

	public ConstraintExceptionDTO setField(String field) {
		this.field = field;
		return this;
	}

	public String getConstraint() {
		return constraint;
	}

	public ConstraintExceptionDTO setConstraint(String constraint) {
		this.constraint = constraint;
		return this;
	}
	
	public String getMessage() {
		return message;
	}

	public ConstraintExceptionDTO setMessage(String message) {
		this.message = message;
		return this;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public ConstraintExceptionDTO setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
}
