package com.dpf.movies.controller;

import com.dpf.movies.dto.MeOutDTO;
import org.springframework.security.core.Authentication;

public interface MeController {

    MeOutDTO me(Authentication authentication);

}
