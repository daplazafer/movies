package com.dpf.movies.common.security;

import com.dpf.movies.common.exception.AuthenticationException;
import com.dpf.movies.dto.CredentialsInDTO;
import com.dpf.movies.dto.UserOutDTO;
import com.dpf.movies.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String passwordIn = (String) auth.getCredentials();

        UserOutDTO user = userService.getUser(CredentialsInDTO.builder()
            .name(auth.getName())
            .passwordHash(passwordIn)
            .build());

        List<GrantedAuthority> roles = user.getRoles().stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(user.getName(), passwordIn, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
