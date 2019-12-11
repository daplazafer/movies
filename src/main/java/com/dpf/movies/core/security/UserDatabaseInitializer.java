package com.dpf.movies.core.security;

import com.dpf.movies.dto.UserInDTO;
import com.dpf.movies.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserDatabaseInitializer {

    @Autowired
    private UserService userService;

    @PostConstruct
    private void initUsers() {
        UserInDTO admin = new UserInDTO("admin", hash("admin"), "ROLE_ADMIN", "ROLE_USER");
        UserInDTO user = new UserInDTO("user", hash("password"), "ROLE_USER");

        userService.save(admin);
        userService.save(user);
    }

    private List<UserInDTO> users(){
        return Stream.of(
                new UserInDTO("admin", hash("admin"), "ROLE_ADMIN", "ROLE_USER"),
                new UserInDTO("user", hash("password"), "ROLE_USER")
        ).collect(Collectors.toList());
    }

    private static String hash(String password){
        return DigestUtils.sha256Hex(password);
    }

}
