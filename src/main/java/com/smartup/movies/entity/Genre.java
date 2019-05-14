package com.smartup.movies.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "genres")
public class Genre implements Serializable {

	private static final long serialVersionUID = 4455372188113100336L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String name;

	public Long getId() {
		return id;
	}

	public Genre setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Genre setName(String name) {
		this.name = name;
		return this;
	}

}
