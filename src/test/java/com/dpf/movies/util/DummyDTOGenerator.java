package com.dpf.movies.util;

import com.dpf.movies.dto.MovieInDTO;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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

}
