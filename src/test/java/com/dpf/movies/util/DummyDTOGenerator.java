package com.dpf.movies.util;

import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.dto.UserInDTO;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.codec.digest.DigestUtils;

public class DummyDTOGenerator {

    private DummyDTOGenerator() {
    }

    public static MovieInDTO createMovieInDTO() {
        return MovieInDTO.builder()
            .title(Random.text())
            .genreId(Random.id())
            .year(Random.number(1990, 2020))
            .actors(Stream.of(Random.id(), Random.id(), Random.id()).collect(Collectors.toList()))
            .build();
    }

    public static UserInDTO createUserInDTO() {
        return UserInDTO.builder()
            .name(Random.text())
            .passwordHash(DigestUtils.sha256Hex(Random.text()))
            .roles(Stream.of(Random.text(), Random.text()).collect(Collectors.toList()))
            .build();
    }

}
