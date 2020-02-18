package com.dpf.movies.controller;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;
import com.dpf.movies.dto.MeOutDTO;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class MeControllerImplTest {

    @InjectMocks
    private MeControllerImpl underTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void givenAuthenticationShouldReturnMeOutDTO() {
        //GIVEN
        String user = "user";
        String role = "ROLE_USER";
        List<SimpleGrantedAuthority> roles = Collections
            .singletonList(new SimpleGrantedAuthority(role));
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
            roles);

        //WHEN
        MeOutDTO result = underTest.me(auth);

        //THEN
        assertNotNull(result);
        assertEquals(user, result.getName());
        assertEquals(roles.size(), result.getRoles().size());
        assertTrue(roles.stream().map(Objects::toString).collect(Collectors.toSet())
            .containsAll(result.getRoles()));
    }

}
