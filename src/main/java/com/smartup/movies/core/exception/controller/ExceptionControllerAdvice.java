package com.smartup.movies.core.exception.controller;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.smartup.movies.core.exception.ConstraintException;
import com.smartup.movies.core.exception.NotFoundException;
import com.smartup.movies.core.exception.dto.ConstraintExceptionDTO;
import com.smartup.movies.core.exception.dto.ExceptionDTO;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDTO> notFoundException(NotFoundException e) {
		return new ResponseEntity<>(
				new ExceptionDTO()
				.setMessage(e.getMessage())
				.setTimestamp(new Date())
				,e.getHttpStatus());
	}
	
	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<ConstraintExceptionDTO> constraintException(ConstraintException e) {
		return new ResponseEntity<>(
				new ConstraintExceptionDTO()
				.setField(e.getField())
				.setConstraint(e.getConstraint())
				.setMessage(e.getMessage())
				.setTimestamp(new Date())
				,e.getHttpStatus());
	}

}
