package com.smartup.movies.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smartup.movies.dto.MovieDTO;
import com.smartup.movies.dto.PerformanceActorDTO;
import com.smartup.movies.dto.PerformanceDTO;
import com.smartup.movies.dto.mapper.ActorMapper;
import com.smartup.movies.dto.mapper.GenreMapper;
import com.smartup.movies.dto.mapper.MovieMapper;
import com.smartup.movies.dto.mapper.PerformanceMapper;
import com.smartup.movies.entity.Actor;
import com.smartup.movies.entity.Movie;
import com.smartup.movies.entity.Performance;
import com.smartup.movies.repository.PerformanceRepository;
import com.smartup.movies.service.ActorService;
import com.smartup.movies.service.GenreService;
import com.smartup.movies.service.MovieService;
import com.smartup.movies.service.PerformanceService;

@Service
public class PerformanceServiceImpl implements PerformanceService {

	private PerformanceRepository performanceRepository;
	private ActorService actorService;
	private MovieService movieService;
	private GenreService genreService;

	@Autowired
	public PerformanceServiceImpl(PerformanceRepository performanceRepository, ActorService actorService,
			MovieService movieService, GenreService genreService) {
		super();
		this.performanceRepository = performanceRepository;
		this.actorService = actorService;
		this.movieService = movieService;
		this.genreService = genreService;
	}

	@Override
	public PerformanceDTO save(PerformanceDTO performanceDTO) {
		Actor actor = ActorMapper.mapToActor(actorService.getById(performanceDTO.getActor_id()));
		MovieDTO movieDTO = movieService.getById(performanceDTO.getMovie_id());
		Movie movie = MovieMapper.mapToMovie(movieDTO, GenreMapper.mapToGenre(genreService.getById(movieDTO.getGenre_id())));
		Performance performance = performanceRepository.save(PerformanceMapper.mapToPerformance(actor, movie));
		return PerformanceMapper.mapToPerformanceDTO(performance);
	}
	
	@Override
	public List<PerformanceActorDTO> getPerformancesByMovieId(Pageable pageable, Long movieId) {
		MovieDTO movieDTO = movieService.getById(movieId);
		Movie movie = MovieMapper.mapToMovie(movieDTO, GenreMapper.mapToGenre(genreService.getById(movieDTO.getGenre_id())));
		Page<Performance> performances = performanceRepository.findById_Movie(pageable, movie);
		List<PerformanceActorDTO> performanceActors = new ArrayList<>();
		for(Performance p: performances) {
			performanceActors.add(PerformanceMapper.mapToPerformanceActorDTO(p));
		}
		return performanceActors;
	}

}
