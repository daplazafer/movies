package com.smartup.movies.dto;

import java.io.Serializable;

public class MovieDTO implements Serializable {

	private static final long serialVersionUID = -3263883194430562263L;
	
	private Long id;
	private String title;
	private Long genre_id;
	private int year;

	public Long getId() {
		return id;
	}

	public MovieDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public MovieDTO setTitle(String title) {
		this.title = title;
		return this;
	}

	public Long getGenre_id() {
		return genre_id;
	}

	public MovieDTO setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
		return this;
	}

	public int getYear() {
		return year;
	}

	public MovieDTO setYear(int year) {
		this.year = year;
		return this;
	}

}
