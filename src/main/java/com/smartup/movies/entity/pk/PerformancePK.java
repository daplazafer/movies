package com.smartup.movies.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.smartup.movies.entity.Actor;
import com.smartup.movies.entity.Movie;

@Embeddable
public class PerformancePK implements Serializable {

	private static final long serialVersionUID = -5996018665289502338L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Actor actor;

	public Movie getMovie() {
		return movie;
	}

	public PerformancePK setMovie(Movie movie) {
		this.movie = movie;
		return this;
	}

	public Actor getActor() {
		return actor;
	}

	public PerformancePK setActor(Actor actor) {
		this.actor = actor;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof PerformancePK)) {
			return false;
		}
		PerformancePK other = (PerformancePK) obj;
		return this.actor.equals(other.actor) && this.movie.equals(other.movie);
	}
	
}
