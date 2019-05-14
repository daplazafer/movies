package com.smartup.movies.test.compare;

import com.smartup.movies.dto.GenreDTO;
import com.smartup.movies.entity.Genre;

public class GenreComparer {
	
	private GenreComparer() {}
	
	public static boolean compareGenreToGenreDTO(Genre g, GenreDTO gdto) {
		return g.getId().equals(gdto.getId()) && g.getName().equals(gdto.getName());
	}

}