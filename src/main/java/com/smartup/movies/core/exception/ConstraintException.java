package com.smartup.movies.core.exception;

import org.springframework.http.HttpStatus;

import com.smartup.movies.core.base.BaseException;

public class ConstraintException extends BaseException {

	private static final long serialVersionUID = 1714030051428708742L;

	private String field;
	private String constraint;

	public ConstraintException(String message, String field, String constraint) {
		super(HttpStatus.BAD_REQUEST, message);
		this.field = field;
		this.constraint = constraint;
	}

	public String getField() {
		return field;
	}

	public ConstraintException setField(String field) {
		this.field = field;
		return this;
	}

	public String getConstraint() {
		return constraint;
	}

	public ConstraintException setConstraint(String constraint) {
		this.constraint = constraint;
		return this;
	}

}
