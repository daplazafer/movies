package com.smartup.movies.dto;

import java.io.Serializable;

public class GenreDTO implements Serializable {

	private static final long serialVersionUID = -2165747834366388431L;
	
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public GenreDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public GenreDTO setName(String name) {
		this.name = name;
		return this;
	}

}
