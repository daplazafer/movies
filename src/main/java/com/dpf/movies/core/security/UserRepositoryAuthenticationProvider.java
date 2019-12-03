package com.dpf.movies.core.security;

import com.dpf.movies.core.exception.AuthenticationException;
import com.dpf.movies.core.exception.NotFoundException;
import com.dpf.movies.domain.User;
import com.dpf.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private @Value("${error.user.login}")
    String ERROR_MESSAGE;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        User user = userRepository.findByName(auth.getName()).orElseThrow(() -> new NotFoundException(ERROR_MESSAGE));

        checkPassword(auth, user);

        List<GrantedAuthority> roles = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(user.getName(), user.getPasswordHash(), roles);
    }

    private void checkPassword(Authentication auth, User user) {
        String inputPasswordHash = (String) auth.getCredentials();
        if (!inputPasswordHash.equals(user.getPasswordHash())) {
            throw new AuthenticationException(ERROR_MESSAGE);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
