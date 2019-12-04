package com.dpf.movies.core.security;

import com.dpf.movies.domain.User;
import com.dpf.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserDatabaseInitializer {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initUsers() {

        User admin = new User(1l, "admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                "ROLE_ADMIN", "ROLE_USER");

        User user = new User(2l, "user", "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
                "ROLE_USER");

        userRepository.save(admin);
        userRepository.save(user);

    }

}
