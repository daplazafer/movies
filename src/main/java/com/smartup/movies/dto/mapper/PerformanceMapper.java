package com.smartup.movies.dto.mapper;

import com.smartup.movies.dto.PerformanceActorDTO;
import com.smartup.movies.dto.PerformanceDTO;
import com.smartup.movies.entity.Actor;
import com.smartup.movies.entity.Movie;
import com.smartup.movies.entity.Performance;
import com.smartup.movies.entity.pk.PerformancePK;

public class PerformanceMapper {
	
	private PerformanceMapper() {}
	
	public static Performance mapToPerformance(Actor actor, Movie movie) {
		return new Performance()
				.setId(new PerformancePK()
						.setActor(actor)
						.setMovie(movie));
	}
	
	public static PerformanceDTO mapToPerformanceDTO(Performance performance) {
		return new PerformanceDTO()
				.setMovie_id(performance.getId().getMovie().getId())
				.setActor_id(performance.getId().getActor().getId());
	}
	
	public static PerformanceActorDTO mapToPerformanceActorDTO(Performance performance) {
		return new PerformanceActorDTO()
				.setActor_id(performance.getId().getActor().getId());
	}

}
