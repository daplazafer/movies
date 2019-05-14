package com.smartup.movies.dto.mapper;

import com.smartup.movies.dto.ActorDTO;
import com.smartup.movies.entity.Actor;

public class ActorMapper {
	
	private ActorMapper(){}
	
	public static ActorDTO mapToActorDTO(Actor actor) {
		return new ActorDTO()
				.setId(actor.getId())
				.setFullName(actor.getFullName());
	}

	public static Actor mapToActor(ActorDTO actor) {
		return new Actor()
				.setId(actor.getId())
				.setFullName(actor.getFullName());
	}
}
