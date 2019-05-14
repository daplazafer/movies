package com.smartup.movies.dto;

import java.io.Serializable;

public class MovieListAllDTO implements Serializable {
	
	private static final long serialVersionUID = 8004252707279304507L;
	
	private Long id;
	private String title;
	private int year;
	
	public Long getId() {
		return id;
	}

	public MovieListAllDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public MovieListAllDTO setTitle(String title) {
		this.title = title;
		return this;
	}

	public int getYear() {
		return year;
	}

	public MovieListAllDTO setYear(int year) {
		this.year = year;
		return this;
	}

}
