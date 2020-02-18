package com.dpf.movies.common.exception;

import com.dpf.movies.common.base.BaseException;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends BaseException {

    public AuthenticationException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid username or password");
    }

}
