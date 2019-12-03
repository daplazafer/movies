package com.dpf.movies.controller.impl;

import com.dpf.movies.controller.UserController;
import com.dpf.movies.core.base.BaseController;
import com.dpf.movies.dto.UserOutDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserControllerImpl extends BaseController implements UserController {

    @GetMapping("${resource.user.me}")
    public ResponseEntity me(Authentication authentication){
        return Optional.ofNullable(authentication)
                .map(auth -> ResponseEntity.ok(UserOutDTO.builder()
                .name(authentication.getName())
                .roles(authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
