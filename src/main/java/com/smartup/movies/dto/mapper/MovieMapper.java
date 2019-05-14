package com.smartup.movies.dto.mapper;

import com.smartup.movies.dto.MovieDTO;
import com.smartup.movies.dto.MovieListAllDTO;
import com.smartup.movies.dto.MovieSaveDTO;
import com.smartup.movies.entity.Genre;
import com.smartup.movies.entity.Movie;

public class MovieMapper {
	
	private MovieMapper() {}

	public static MovieDTO mapToMovieDTO(Movie movie) {
		return new MovieDTO()
				.setId(movie.getId())
				.setTitle(movie.getTitle())
				.setGenre_id(movie.getGenre().getId())
				.setYear(movie.getYear());
	}
	
	public static MovieListAllDTO mapToMovieListAllDTO(Movie movie) {
		return new MovieListAllDTO()
				.setId(movie.getId())
				.setTitle(movie.getTitle())
				.setYear(movie.getYear());
	}
	
	public static Movie mapToMovie(MovieDTO movieDTO, Genre genre) {
		return new Movie()
				.setId(movieDTO.getId())
				.setTitle(movieDTO.getTitle())
				.setYear(movieDTO.getYear())
				.setGenre(genre);
	}
	
	public static Movie mapToMovie(MovieSaveDTO movieDTO, Genre genre) {
		return new Movie()
				.setTitle(movieDTO.getTitle())
				.setYear(movieDTO.getYear())
				.setGenre(genre);
	}

}
