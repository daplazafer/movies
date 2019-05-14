package com.smartup.movies.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.smartup.movies.entity.pk.PerformancePK;

@Entity
@Table(name = "performances")
public class Performance implements Serializable {

	private static final long serialVersionUID = -1865968178006727905L;

	@EmbeddedId
	private PerformancePK id;

	public PerformancePK getId() {
		return id;
	}

	public Performance setId(PerformancePK id) {
		this.id = id;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Performance)) {
			return false;
		}
		Performance other = (Performance) obj;
		return this.id.equals(other.id);
	}
	
}
