package com.dpf.movies.controller;

import com.dpf.movies.common.base.BaseController;
import com.dpf.movies.dto.MeOutDTO;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeControllerImpl extends BaseController implements MeController {

    @GetMapping("${resource.user.me}")
    @ResponseStatus(HttpStatus.OK)
    public MeOutDTO me(Authentication authentication) {
        return MeOutDTO.builder()
            .name(authentication.getName())
            .roles(authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
            .build();
    }

}
