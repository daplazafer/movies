package com.smartup.movies.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartup.movies.core.exception.ConstraintException;
import com.smartup.movies.core.exception.NotFoundException;
import com.smartup.movies.dto.MovieDTO;
import com.smartup.movies.dto.MovieListAllDTO;
import com.smartup.movies.dto.MovieSaveDTO;
import com.smartup.movies.dto.mapper.GenreMapper;
import com.smartup.movies.entity.Movie;
import com.smartup.movies.repository.MovieRepository;
import com.smartup.movies.service.MovieService;
import com.smartup.movies.service.impl.MovieServiceImpl;
import com.smartup.movies.test.compare.MovieComparer;
import com.smartup.movies.test.dummy.MovieDummyCreator;

@RunWith(SpringRunner.class)
public class MovieServiceTests {

	private MovieService movieService;

	@Mock
	private MovieRepository movieRepository;

	@Mock
	private GenreService genreService;

	@Before
	public void setUp() {
		movieService = new MovieServiceImpl(movieRepository, genreService);
	}

	@Test
	public void testGetAllFullList() {
		// Given
		Pageable pageable = PageRequest.of(0, 1000);
		List<Movie> movies = new ArrayList<>();
		movies.add(MovieDummyCreator.random());
		Page<Movie> pagedResponse = new PageImpl<>(movies);
		when(movieRepository.findAll(pageable)).thenReturn(pagedResponse);

		// When
		List<MovieListAllDTO> movieList = movieService.getAll(pageable);

		// Then
		assertTrue("Retrieved list does not match with saved one", MovieComparer.compareMovieLists(movies, movieList));
	}

	@Test
	public void testGetAllEmpty() {
		// Given
		Pageable pageable = PageRequest.of(5, 100);
		Page<Movie> pagedResponse = new PageImpl<>(new ArrayList<>());
		when(movieRepository.findAll(pageable)).thenReturn(pagedResponse);

		// When
		List<MovieListAllDTO> movieList = movieService.getAll(pageable);

		// Then
		assertTrue("Retrieved list is not empty", movieList.isEmpty());
	}

	@Test
	public void testGetByIdSuccess() {
		// Given
		Long id = 1l;
		Movie movie = MovieDummyCreator.random();
		when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

		// When
		MovieDTO movieDTO = movieService.getById(id);

		// Then
		assertTrue("Retrieved movie does not match with saved one", MovieComparer.compareMovieToMovieDTO(movie, movieDTO));
	}

	@Test(expected = NotFoundException.class)
	public void testGetByIdNotFound() {
		// Given
		Long id = 99l;
		when(movieRepository.findById(id)).thenReturn(Optional.empty());

		// When
		movieService.getById(id);

		// Then
		fail("NotFoundException has not been thrown when empty movie is retrieved by repository");
	}

	@Test
	public void testSaveSuccess() {
		// Given
		Movie movie = MovieDummyCreator.random();
		when(movieRepository.save(movie)).thenReturn(movie);
		when(genreService.getById(movie.getGenre().getId())).thenReturn(GenreMapper.mapToGenreDTO(movie.getGenre()));

		// When
		MovieDTO movieDTO = movieService.save(new MovieSaveDTO().setGenre_id(movie.getGenre().getId())
				.setTitle(movie.getTitle()).setYear(movie.getYear()));

		// Then
		assertTrue("Saved movie does not match with generated one", MovieComparer.compareMovieToMovieDTO(movie, movieDTO));
	}

	@Test(expected = ConstraintException.class)
	public void testSaveConstraintViolation() {
		// Given
		Movie movie = MovieDummyCreator.random().setYear(Movie.YEAR_MIN - 1);
		when(movieRepository.save(movie)).thenThrow(new ConstraintViolationException(new HashSet<>()));
		when(genreService.getById(movie.getGenre().getId())).thenReturn(GenreMapper.mapToGenreDTO(movie.getGenre()));

		// When
		movieService.save(new MovieSaveDTO().setGenre_id(movie.getGenre().getId()).setTitle(movie.getTitle())
				.setYear(movie.getYear()));

		// Then
		fail("ConstraintException has not been thrown trying to save an invalid movie");

	}

}
