package com.smartup.movies.dto.mapper;

import com.smartup.movies.dto.GenreDTO;
import com.smartup.movies.entity.Genre;

public class GenreMapper {
	
	private GenreMapper() {}
	
	public static GenreDTO mapToGenreDTO(Genre genre) {
		return new GenreDTO()
				.setId(genre.getId())
				.setName(genre.getName());	
	}
	
	public static Genre mapToGenre(GenreDTO genre) {
		return new Genre()
				.setId(genre.getId())
				.setName(genre.getName());
	}
	
}
