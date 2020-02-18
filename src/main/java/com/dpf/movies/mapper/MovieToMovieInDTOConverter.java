package com.dpf.movies.mapper;

import com.dpf.movies.domain.Actor;
import com.dpf.movies.domain.Movie;
import com.dpf.movies.domain.Performance;
import com.dpf.movies.domain.pk.PerformancePK;
import com.dpf.movies.dto.MovieInDTO;
import java.util.stream.Collectors;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class MovieToMovieInDTOConverter extends CustomConverter<Movie, MovieInDTO> {

    @Override
    public MovieInDTO convert(Movie movie, Type<? extends MovieInDTO> type,
        MappingContext mappingContext) {
        return MovieInDTO.builder()
            .title(movie.getTitle())
            .year(movie.getYear())
            .actors(movie.getPerformances().stream()
                .map(Performance::getId)
                .map(PerformancePK::getActor)
                .map(Actor::getId)
                .collect(Collectors.toList()))
            .genreId(movie.getGenre().getId())
            .build();
    }

}
