package com.dpf.movies.core.exception.controller;

import com.dpf.movies.core.exception.NotFoundException;
import com.dpf.movies.core.exception.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> notFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                ExceptionDTO.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                , HttpStatus.NOT_FOUND);
    }

}
