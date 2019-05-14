package com.smartup.movies.dto;

import java.io.Serializable;

public class PerformanceDTO implements Serializable{
	
	private static final long serialVersionUID = -2527253836170170051L;
	
	private Long movie_id;
	private Long actor_id;

	public Long getMovie_id() {
		return movie_id;
	}

	public PerformanceDTO setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
		return this;
	}

	public Long getActor_id() {
		return actor_id;
	}

	public PerformanceDTO setActor_id(Long actor_id) {
		this.actor_id = actor_id;
		return this;
	}

}
