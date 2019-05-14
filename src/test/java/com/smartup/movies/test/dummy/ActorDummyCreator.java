package com.smartup.movies.test.dummy;

import java.util.Random;

import com.smartup.movies.entity.Actor;

public class ActorDummyCreator {
	
	private ActorDummyCreator() {}
	
	public static Actor random() { 
		Long id = new Random().nextLong();
		return new Actor().setId(id).setFullName(Actor.class.getSimpleName()+id);
	}

}
