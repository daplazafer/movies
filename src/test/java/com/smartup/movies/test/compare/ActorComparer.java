package com.smartup.movies.test.compare;

import com.smartup.movies.dto.ActorDTO;
import com.smartup.movies.entity.Actor;

public class ActorComparer {
	
	private ActorComparer() {}
	
	public static boolean compareActorToActorDTO(Actor a, ActorDTO adto) {
		return a.getId().equals(adto.getId()) && a.getFullName().equals(adto.getFullName());
	}

}
