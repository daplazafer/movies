package com.smartup.movies.test.dummy;

import java.util.Random;

import com.smartup.movies.entity.Genre;

public class GenreDummyCreator {
	
	private GenreDummyCreator() {}
	
	public static Genre random() {
		Long id = new Random().nextLong();
		return new Genre().setId(id).setName(Genre.class.getSimpleName()+id);
	}

}
