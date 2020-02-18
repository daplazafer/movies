package com.dpf.movies.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import com.dpf.movies.common.util.Hasher;
import com.dpf.movies.domain.User;
import com.dpf.movies.dto.UserInDTO;
import com.dpf.movies.dto.UserOutDTO;
import com.dpf.movies.repository.UserRepository;
import com.dpf.movies.util.DummyDTOGenerator;
import java.util.stream.Collectors;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Hasher hasher;

    @Mock
    private MapperFacade mapper;

    @InjectMocks
    private UserServiceImpl underTest;

    private static String hash(String value) {
        return DigestUtils.sha512Hex(value);
    }

    @Before
    public void setUp() {
        initMocks(this);
        ReflectionTestUtils.setField(underTest, "mapper", mapper);
    }

    @Test
    public void saveTest() {
        //GIVEN
        UserInDTO userInDTO = DummyDTOGenerator.createUserInDTO();
        User user = User.builder()
            .id(1l)
            .name(userInDTO.getName())
            .passwordHash(hash(userInDTO.getPasswordHash()))
            .roles(userInDTO.getRoles())
            .build();
        UserOutDTO userOutDTO = UserOutDTO.builder()
            .name(user.getName())
            .roles(user.getRoles())
            .build();

        when(userRepository.save(any(User.class)))
            .thenReturn(user);
        when(hasher.from(userInDTO.getPasswordHash()))
            .thenReturn(hash(userInDTO.getPasswordHash()));
        when(mapper.map(user, UserOutDTO.class))
            .thenReturn(userOutDTO);

        //WHEN
        UserOutDTO result = underTest.save(userInDTO);

        //THEN
        assertNotNull(result);
        assertEquals(userInDTO.getName(), result.getName());
        assertEquals(userInDTO.getRoles().size(), result.getRoles().size());
        assertTrue(userInDTO.getRoles().stream().collect(Collectors.toSet())
            .containsAll(result.getRoles()));
    }

}
