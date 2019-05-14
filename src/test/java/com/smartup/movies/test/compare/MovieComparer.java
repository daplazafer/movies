package com.smartup.movies.test.compare;

import java.util.Iterator;

import com.smartup.movies.dto.MovieDTO;
import com.smartup.movies.dto.MovieListAllDTO;
import com.smartup.movies.entity.Movie;

public class MovieComparer {

	private MovieComparer() {
	}

	public static boolean compareMovieToMovieListAllDTO(Movie m, MovieListAllDTO mdto) {
		return m.getId().equals(mdto.getId()) && mdto.getTitle().equals(m.getTitle()) && mdto.getYear() == m.getYear();
	}

	public static boolean compareMovieToMovieDTO(Movie m, MovieDTO mdto) {
		return m.getId().equals(mdto.getId()) && mdto.getTitle().equals(m.getTitle()) && mdto.getYear() == m.getYear()
				&& mdto.getGenre_id().equals(m.getGenre().getId());
	}

	public static boolean compareMovieLists(Iterable<Movie> a, Iterable<MovieListAllDTO> b) {
		Iterator<Movie> ita = a.iterator();
		Iterator<MovieListAllDTO> itb = b.iterator();
		while (ita.hasNext() && itb.hasNext())
			if (!compareMovieToMovieListAllDTO(ita.next(), itb.next()))
				return false;
		if (ita.hasNext() != itb.hasNext())
			return false;
		return true;
	}

}
