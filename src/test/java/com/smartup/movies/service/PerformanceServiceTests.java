package com.smartup.movies.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartup.movies.dto.PerformanceActorDTO;
import com.smartup.movies.dto.PerformanceDTO;
import com.smartup.movies.dto.mapper.ActorMapper;
import com.smartup.movies.dto.mapper.GenreMapper;
import com.smartup.movies.dto.mapper.MovieMapper;
import com.smartup.movies.entity.Actor;
import com.smartup.movies.entity.Movie;
import com.smartup.movies.entity.Performance;
import com.smartup.movies.entity.pk.PerformancePK;
import com.smartup.movies.repository.PerformanceRepository;
import com.smartup.movies.service.impl.PerformanceServiceImpl;
import com.smartup.movies.test.compare.PerformanceComparer;
import com.smartup.movies.test.dummy.ActorDummyCreator;
import com.smartup.movies.test.dummy.MovieDummyCreator;

@RunWith(SpringRunner.class)
public class PerformanceServiceTests {

	private PerformanceService performanceService;

	@Mock
	PerformanceRepository performanceRepository;

	@Mock
	ActorService actorService;

	@Mock
	MovieService movieService;

	@Mock
	GenreService genreService;

	@Before
	public void setUp() {
		performanceService = new PerformanceServiceImpl(performanceRepository, actorService, movieService,
				genreService);
	}

	@Test
	public void testSaveSuccess() {
		// Given
		Actor actor = ActorDummyCreator.random();
		Movie movie = MovieDummyCreator.random();
		PerformancePK performancePK = new PerformancePK().setActor(actor).setMovie(movie);
		Performance performance = new Performance().setId(performancePK);
		when(performanceRepository.save(performance)).thenReturn(performance);
		when(performanceRepository.findById(performancePK)).thenReturn(Optional.of(performance));
		when(genreService.getById(movie.getGenre().getId())).thenReturn(GenreMapper.mapToGenreDTO(movie.getGenre()));
		when(movieService.getById(movie.getId())).thenReturn(MovieMapper.mapToMovieDTO(movie));
		when(actorService.getById(actor.getId())).thenReturn(ActorMapper.mapToActorDTO(actor));

		// When
		PerformanceDTO performanceDTO = performanceService
				.save(new PerformanceDTO().setActor_id(actor.getId()).setMovie_id(movie.getId()));

		// Then
		assertTrue("Saved performance does not match with generated one",
				PerformanceComparer.comparePerformanceToPerformanceDTO(performance, performanceDTO));
	}

	@Test
	public void TestGetPerformancesByMovieIdFullList() {
		// Given
		Pageable pageable = PageRequest.of(0, 1000);
		Movie movie = MovieDummyCreator.random();
		Page<Performance> pagedResponse = new PageImpl<>(movie.getPerformances());
		when(performanceRepository.findById_Movie(pageable, movie)).thenReturn(pagedResponse);
		when(movieService.getById(movie.getId())).thenReturn(MovieMapper.mapToMovieDTO(movie));
		when(genreService.getById(movie.getGenre().getId())).thenReturn(GenreMapper.mapToGenreDTO(movie.getGenre()));

		// When
		List<PerformanceActorDTO> performanceList = performanceService.getPerformancesByMovieId(pageable,
				movie.getId());

		// Then
		assertTrue("Retrieved list does not match with saved one",
				PerformanceComparer.comparePerformanceLists(movie.getPerformances(), performanceList));
	}

	@Test
	public void TestGetPerformancesByMovieIdEmpty() {
		// Given
		Pageable pageable = PageRequest.of(5, 100);
		Movie movie = MovieDummyCreator.random();
		Page<Performance> pagedResponse = new PageImpl<>(new ArrayList<>());
		when(performanceRepository.findById_Movie(pageable, movie)).thenReturn(pagedResponse);
		when(movieService.getById(movie.getId())).thenReturn(MovieMapper.mapToMovieDTO(movie));
		when(genreService.getById(movie.getGenre().getId())).thenReturn(GenreMapper.mapToGenreDTO(movie.getGenre()));

		// When
		List<PerformanceActorDTO> performanceList = performanceService.getPerformancesByMovieId(pageable, movie.getId());

		// Then
		assertTrue("Retrieved list is not empty", performanceList.isEmpty());
	}

}
