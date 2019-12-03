package com.dpf.movies.core.exception;

import com.dpf.movies.core.base.BaseException;
import org.springframework.http.HttpStatus;

public class JwtMatchingException extends BaseException {

    public JwtMatchingException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
