package com.smartup.movies.dto;

import java.io.Serializable;

public class PerformanceActorDTO implements Serializable {
	
	private static final long serialVersionUID = -7703682795002914280L;
	
	private Long actor_id;

	public Long getActor_id() {
		return actor_id;
	}

	public PerformanceActorDTO setActor_id(Long actor_id) {
		this.actor_id = actor_id;
		return this;
	}

}
