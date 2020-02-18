package com.dpf.movies.common.exception;

import com.dpf.movies.common.base.BaseException;
import org.springframework.http.HttpStatus;

public class JwtMatchingException extends BaseException {

    public JwtMatchingException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
