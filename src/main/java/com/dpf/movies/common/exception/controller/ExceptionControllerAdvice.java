package com.dpf.movies.common.exception.controller;

import com.dpf.movies.common.base.BaseException;
import com.dpf.movies.common.exception.dto.ExceptionDTO;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO defaultException(Exception e) {
        return ExceptionDTO.builder()
            .message(e.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionDTO> baseException(BaseException e) {
        return ResponseEntity.status(e.getHttpStatus())
            .body(ExceptionDTO.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

}
