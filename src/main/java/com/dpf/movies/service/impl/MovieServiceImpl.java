package com.dpf.movies.service.impl;

import com.dpf.movies.core.base.BaseService;
import com.dpf.movies.core.exception.NotFoundException;
import com.dpf.movies.domain.Actor;
import com.dpf.movies.domain.Genre;
import com.dpf.movies.domain.Movie;
import com.dpf.movies.domain.Performance;
import com.dpf.movies.domain.pk.PerformancePK;
import com.dpf.movies.dto.MovieDetailOutDTO;
import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.dto.MovieOutDTO;
import com.dpf.movies.repository.MovieRepository;
import com.dpf.movies.service.ActorService;
import com.dpf.movies.service.GenreService;
import com.dpf.movies.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@PropertySource("classpath:error.properties")
public class MovieServiceImpl extends BaseService implements MovieService {

    private MovieRepository movieRepository;
    private GenreService genreService;
    private ActorService actorService;

    private @Value("${error.movie.notfound}")
    String ERROR_NOTFOUND;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, GenreService genreService, ActorService actorService) {
        super();
        this.movieRepository = movieRepository;
        this.genreService = genreService;
        this.actorService = actorService;
    }

    @Override
    public List<MovieOutDTO> getAll(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        return getMapper().mapAsList(movies.getContent(), MovieOutDTO.class);
    }

    @Override
    public MovieDetailOutDTO getById(Long id) {
        Movie movie = getMovie(id);
        return getMapper().map(movie, MovieDetailOutDTO.class);
    }

    @Override
    public MovieDetailOutDTO save(MovieInDTO movieInDTO) {
        Movie movie = mapToMovie(movieInDTO);
        Movie newMovie = movieRepository.save(movie);
        return getMapper().map(newMovie, MovieDetailOutDTO.class);
    }

    @Override
    public MovieDetailOutDTO update(Long id, MovieInDTO movieInDTO) {
        getMovie(id);
        Movie movieToUpdate = mapToMovie(movieInDTO);
        movieToUpdate.setId(id);
        Movie updatedMovie = movieRepository.save(movieToUpdate);
        return getMapper().map(updatedMovie, MovieDetailOutDTO.class);
    }

    @Override
    public MovieDetailOutDTO partialUpdate(Long id, MovieInDTO partialMovie) {
        ObjectMapper objectMapper = new ObjectMapper();

        Movie currentMovie = getMovie(id);
        Map<String, Object> currentMovieMap = objectMapper.convertValue(currentMovie, Map.class);

        Map<String, Object> partialMovieMap = objectMapper.convertValue(mapToMovie(partialMovie), Map.class);
        partialMovieMap.entrySet().stream()
                .filter(entry -> isNull(entry.getValue()))
                .map(Map.Entry::getKey)
                .forEach(key -> partialMovieMap.remove(key));

        currentMovieMap.putAll(partialMovieMap);

        Movie newMovie = objectMapper.convertValue(currentMovieMap, Movie.class);
        return update(id, getMapper().map(newMovie, MovieInDTO.class));
    }

    @Override
    public void delete(Long id) {
        getMovie(id);
        movieRepository.deleteById(id);
    }

    private Movie getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException(ERROR_NOTFOUND + id.toString()));
    }

    private Movie mapToMovie(MovieInDTO movieInDTO) {
        Movie movie = getMapper().map(movieInDTO, Movie.class);
        movie.setGenre(getMapper().map(genreService.getById(movieInDTO.getGenreId()), Genre.class));
        Optional.ofNullable(movieInDTO.getActors()).ifPresent(actors -> movie.setPerformances(actors.stream()
                .map(id -> getMapper().map(actorService.getById(id), Actor.class))
                .map(actor -> Performance.builder()
                        .id(PerformancePK.builder()
                                .actor(actor)
                                .movie(movie)
                                .build())
                        .build())
                .collect(Collectors.toList())));
        return movie;
    }

}
