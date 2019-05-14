package com.smartup.movies.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "actors")
public class Actor implements Serializable{

	private static final long serialVersionUID = 4567843510731182934L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String fullName;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "actor_id")
	private List<Performance> performances;

	public Long getId() {
		return id;
	}

	public Actor setId(Long id) {
		this.id = id;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public Actor setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Actor)) {
			return false;
		}
		Actor other = (Actor) obj;
		return this.id == other.id && this.fullName.equals(other.fullName);
	}

	public List<Performance> getPerformances() {
		return performances;
	}

	public void setPerformances(List<Performance> performances) {
		this.performances = performances;
	}
}
