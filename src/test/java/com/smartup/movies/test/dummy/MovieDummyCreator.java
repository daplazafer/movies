package com.smartup.movies.test.dummy;

import java.util.ArrayList;
import java.util.Random;

import com.smartup.movies.entity.Movie;
import com.smartup.movies.entity.Performance;
import com.smartup.movies.entity.pk.PerformancePK;

public class MovieDummyCreator {

	private MovieDummyCreator() {
	}

	public static Movie random() {
		Long id = new Random().nextLong();
		Movie movie = new Movie()
				.setId(id)
				.setTitle(Movie.class.getSimpleName() + id)
				.setYear(randomInt(Movie.YEAR_MIN, Movie.YEAR_MAX)).setGenre(GenreDummyCreator.random())
				.setPerformances(new ArrayList<>());
		for (int i = 0; i < randomInt(1, 10); i++) {
			movie.getPerformances().add(
					new Performance()
					.setId(new PerformancePK()
							.setActor(ActorDummyCreator.random())
							.setMovie(movie)));
		}
		return movie;
	}

	private static int randomInt(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}

}
