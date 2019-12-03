package com.dpf.movies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserController {

    ResponseEntity me(Authentication authentication);

}
