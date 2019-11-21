package com.dpf.movies.dto.mapper;

import com.dpf.movies.domain.Movie;
import com.dpf.movies.dto.MovieInDTO;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class MovieInDTOToMovieConverter extends CustomConverter<MovieInDTO, Movie> {

    @Override
    public Movie convert(MovieInDTO movieInDTO, Type<? extends Movie> type, MappingContext mappingContext) {
        return Movie.builder()
                .title(movieInDTO.getTitle())
                .year(movieInDTO.getYear())
                .build();
    }

}
