package com.dpf.movies.core.exception;

import com.dpf.movies.core.base.BaseException;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends BaseException {

    public AuthenticationException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid username or password");
    }

}
