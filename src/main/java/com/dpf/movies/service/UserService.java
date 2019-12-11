package com.dpf.movies.service;

import com.dpf.movies.dto.CredentialsInDTO;
import com.dpf.movies.dto.UserInDTO;
import com.dpf.movies.dto.UserOutDTO;

public interface UserService {

    UserOutDTO save(UserInDTO userInDTO);

    UserOutDTO getUser(CredentialsInDTO userInDTO);

}
