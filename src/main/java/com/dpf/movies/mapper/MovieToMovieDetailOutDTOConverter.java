package com.dpf.movies.mapper;

import com.dpf.movies.domain.Movie;
import com.dpf.movies.domain.Performance;
import com.dpf.movies.domain.pk.PerformancePK;
import com.dpf.movies.dto.ActorOutDTO;
import com.dpf.movies.dto.GenreOutDTO;
import com.dpf.movies.dto.MovieDetailOutDTO;
import java.util.stream.Collectors;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class MovieToMovieDetailOutDTOConverter extends CustomConverter<Movie, MovieDetailOutDTO> {

    @Override
    public MovieDetailOutDTO convert(Movie movie, Type<? extends MovieDetailOutDTO> type,
        MappingContext mappingContext) {
        return MovieDetailOutDTO.builder()
            .id(movie.getId())
            .title(movie.getTitle())
            .year(movie.getYear())
            .genre(mapperFacade.map(movie.getGenre(), GenreOutDTO.class))
            .actors(mapperFacade.mapAsList(movie.getPerformances().stream()
                    .map(Performance::getId)
                    .map(PerformancePK::getActor)
                    .collect(Collectors.toList())
                , ActorOutDTO.class))
            .build();
    }

}
