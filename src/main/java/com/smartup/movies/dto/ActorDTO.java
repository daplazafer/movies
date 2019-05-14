package com.smartup.movies.dto;

import java.io.Serializable;

public class ActorDTO implements Serializable{

	private static final long serialVersionUID = 6126279323284880685L;
	
	private Long id;
	private String fullName;

	public Long getId() {
		return id;
	}

	public ActorDTO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public ActorDTO setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

}
