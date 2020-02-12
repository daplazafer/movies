package com.dpf.movies.service.impl;

import com.dpf.movies.core.base.BaseService;
import com.dpf.movies.core.exception.AuthenticationException;
import com.dpf.movies.core.util.Hasher;
import com.dpf.movies.domain.User;
import com.dpf.movies.dto.CredentialsInDTO;
import com.dpf.movies.dto.UserInDTO;
import com.dpf.movies.dto.UserOutDTO;
import com.dpf.movies.repository.UserRepository;
import com.dpf.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    private UserRepository userRepository;

    private Hasher hasher;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Hasher hasher) {
        this.userRepository = userRepository;
        this.hasher = hasher;
    }

    @Override
    public UserOutDTO save(UserInDTO userInDTO){
        User user = User.builder()
                .name(userInDTO.getName())
                .passwordHash(hasher.from(userInDTO.getPasswordHash()))
                .roles(userInDTO.getRoles())
                .build();
        User newUser = userRepository.save(user);
        return getMapper().map(newUser, UserOutDTO.class);
    }

    @Override
    public UserOutDTO getUser(CredentialsInDTO credentialsInDTO){
        String inName = Optional.ofNullable(credentialsInDTO.getName()).orElseThrow(()-> new IllegalArgumentException());
        String inPass = Optional.ofNullable(credentialsInDTO.getPasswordHash()).orElseThrow(()-> new IllegalArgumentException());

        User user = userRepository.findByName(inName).orElseThrow(()-> newAuthenticationException());

        if(user.getPasswordHash().equals(hasher.from(inPass))){
            return getMapper().map(user, UserOutDTO.class);
        } else {
            throw newAuthenticationException();
        }
    }

    private AuthenticationException newAuthenticationException() {
        return new AuthenticationException();
    }

}
