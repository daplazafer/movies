package com.smartup.movies.dto;

import java.io.Serializable;

public class MovieSaveDTO implements Serializable {

	private static final long serialVersionUID = -6842438604737765005L;

	private String title;
	private Long genre_id;
	private int year;

	public String getTitle() {
		return title;
	}

	public MovieSaveDTO setTitle(String title) {
		this.title = title;
		return this;
	}

	public Long getGenre_id() {
		return genre_id;
	}

	public MovieSaveDTO setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
		return this;
	}

	public int getYear() {
		return year;
	}

	public MovieSaveDTO setYear(int year) {
		this.year = year;
		return this;
	}


}
