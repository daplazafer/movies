package com.dpf.movies.common.exception;

import com.dpf.movies.common.base.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
