package com.smartup.movies.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smartup.movies.core.exception.ConstraintException;
import com.smartup.movies.core.exception.NotFoundException;
import com.smartup.movies.dto.MovieDTO;
import com.smartup.movies.dto.MovieListAllDTO;
import com.smartup.movies.dto.MovieSaveDTO;
import com.smartup.movies.dto.mapper.GenreMapper;
import com.smartup.movies.dto.mapper.MovieMapper;
import com.smartup.movies.entity.Movie;
import com.smartup.movies.repository.MovieRepository;
import com.smartup.movies.service.GenreService;
import com.smartup.movies.service.MovieService;

@Service
@PropertySource("classpath:error.properties")
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;
	private GenreService genreService;

	private @Value("${error.movie.notfound}") String ERROR_NOTFOUND;
	private @Value("${error.movie.constraint}") String ERROR_CONSTRAINT;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository, GenreService genreService) {
		super();
		this.movieRepository = movieRepository;
		this.genreService = genreService;
	}

	@Override
	public List<MovieListAllDTO> getAll(Pageable pageable) {
		Page<Movie> movies = movieRepository.findAll(pageable);
		List<MovieListAllDTO> moviesDTO = new ArrayList<>();
		for (Movie m : movies) {
			moviesDTO.add(MovieMapper.mapToMovieListAllDTO(m));
		}
		return moviesDTO;
	}

	@Override
	public MovieDTO getById(Long id) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ERROR_NOTFOUND + id.toString()));
		return MovieMapper.mapToMovieDTO(movie);
	}

	@Override
	public MovieDTO save(MovieSaveDTO movieDTO) {
		Movie movie = null;
		try {
			movie = movieRepository.save(MovieMapper.mapToMovie(movieDTO,
					GenreMapper.mapToGenre(genreService.getById(movieDTO.getGenre_id()))));
		} catch (ConstraintViolationException e) {
			if(e.getConstraintViolations().size()>0) {
				ConstraintViolation<?> cv = e.getConstraintViolations().iterator().next();
				throw new ConstraintException(ERROR_CONSTRAINT, cv.getPropertyPath().toString(), cv.getMessage());
			}else {
				throw new ConstraintException(ERROR_CONSTRAINT, "", "");
			}
		}
		return MovieMapper.mapToMovieDTO(movie);
	}

}
