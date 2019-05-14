package com.smartup.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.smartup.movies.dto.ActorDTO;
import com.smartup.movies.dto.GenreDTO;
import com.smartup.movies.dto.MovieDTO;
import com.smartup.movies.dto.MovieListAllDTO;
import com.smartup.movies.dto.MovieSaveDTO;
import com.smartup.movies.dto.PerformanceActorDTO;
import com.smartup.movies.dto.PerformanceDTO;
import com.smartup.movies.service.ActorService;
import com.smartup.movies.service.GenreService;
import com.smartup.movies.service.MovieService;
import com.smartup.movies.service.PerformanceService;

@RestController
@RequestMapping(MovieRestController.API_MOVIES)
public class MovieRestController {

	public static final String API_MOVIES = "/api/movies";

	public static final String PATHVARIABLE_ID = "id";
	public static final String API_MOVIES_ALL = "/";
	public static final String API_MOVIES_ID = "/{"+PATHVARIABLE_ID+"}";
	public static final String API_MOVIES_ADD = "/";
	public static final String API_MOVIES_PERFORMANCE = "/performances";

	public static final String REQUEST_PARAM_PAGE = "page";
	public static final String REQUEST_PARAM_PAGE_DEFAULT = "0";
	public static final String REQUEST_PARAM_LIMIT = "limit";
	public static final String REQUEST_PARAM_LIMIT_DEFAULT = "50";

	private MovieService movieService;
	private GenreService genreService;
	private ActorService actorService;
	private PerformanceService performanceService;
	
	@Autowired
	public MovieRestController(MovieService movieService, GenreService genreService, ActorService actorService,
			PerformanceService performanceService) {
		super();
		this.movieService = movieService;
		this.genreService = genreService;
		this.actorService = actorService;
		this.performanceService = performanceService;
	}

	/**
	 * List all movies
	 * 
	 * @param page count
	 * @param limit page items
	 * @return OK: list with all movies in the page / NO_CONTENT
	 */
	@GetMapping(value = API_MOVIES_ALL)
	public ResponseEntity<List<MovieListAllDTO>> listAll(
			@RequestParam(value = REQUEST_PARAM_PAGE, defaultValue = REQUEST_PARAM_PAGE_DEFAULT) int page,
			@RequestParam(value = REQUEST_PARAM_LIMIT, defaultValue = REQUEST_PARAM_LIMIT_DEFAULT) int limit) {	
		Pageable pageable = PageRequest.of(page, limit);
		List<MovieListAllDTO> movies = movieService.getAll(pageable);
		if (movies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	/**
	 * Detail of a movie
	 * 
	 * @param id of the movie
	 * @return OK: all fields of the movie
	 */
	@GetMapping(value = API_MOVIES_ID)
	public ResponseEntity<?> detail(@PathVariable(PATHVARIABLE_ID) long id) {
		MovieDTO movie = movieService.getById(id);
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}

	/**
	 * Store a movie
	 * 
	 * @param movie to be stored
	 * @return CREATED: movie stored in persistence
	 */
	@PostMapping(value = API_MOVIES_ADD)
	public ResponseEntity<?> storeMovie(@RequestBody MovieSaveDTO movie, UriComponentsBuilder ucBuilder) {
		GenreDTO genre = genreService.getById(movie.getGenre_id());
		movie.setGenre_id(genre.getId());
		MovieDTO newMovie = movieService.save(movie);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path(API_MOVIES + API_MOVIES_ID).buildAndExpand(newMovie.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	/**
	 * List performances of a movie
	 * 
	 * @param page count
	 * @param limit page items
	 * @param movieId id of the movie
	 * @return OK: list with all performances in the movie / NO_CONTENT
	 */
	@GetMapping(value = API_MOVIES_ID+API_MOVIES_PERFORMANCE)
	public ResponseEntity<List<PerformanceActorDTO>> listPerformances(@PathVariable(PATHVARIABLE_ID) long movieId,
			@RequestParam(value = REQUEST_PARAM_PAGE, defaultValue = REQUEST_PARAM_PAGE_DEFAULT) int page,
			@RequestParam(value = REQUEST_PARAM_LIMIT, defaultValue = REQUEST_PARAM_LIMIT_DEFAULT) int limit) {	
		Pageable pageable = PageRequest.of(page, limit);
		List<PerformanceActorDTO> performances = performanceService.getPerformancesByMovieId(pageable, movieId);
		if (performances.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(performances, HttpStatus.OK);
	}	

	/**
	 * Save a performance
	 * 
	 * @param performance to be saved
	 * @return CREATED
	 */
	@PostMapping(value = API_MOVIES_ID+API_MOVIES_PERFORMANCE)
	public ResponseEntity<?> savePerformance(@PathVariable(PATHVARIABLE_ID) long movieId, @RequestBody PerformanceActorDTO peformance) {
		MovieDTO movie = movieService.getById(movieId);
		ActorDTO actor = actorService.getById(peformance.getActor_id());
		PerformanceDTO performance = new PerformanceDTO()
				.setMovie_id(movie.getId())
				.setActor_id(actor.getId());
		performanceService.save(performance);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
