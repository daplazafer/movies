package com.smartup.movies.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

	private static final long serialVersionUID = 6307313849485033852L;
	
	public static final int YEAR_MIN = 1800;
	public static final int YEAR_MAX = 2200;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	private Genre genre;

	@Min(YEAR_MIN)
	@Max(YEAR_MAX)
	private int year;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_id")
	private List<Performance> performances;

	public Long getId() {
		return id;
	}

	public Movie setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Movie setTitle(String title) {
		this.title = title;
		return this;
	}

	public Genre getGenre() {
		return genre;
	}

	public Movie setGenre(Genre genre) {
		this.genre = genre;
		return this;
	}

	public int getYear() {
		return year;
	}

	public Movie setYear(int year) {
		this.year = year;
		return this;
	}

	public List<Performance> getPerformances() {
		return performances;
	}

	public Movie setPerformances(List<Performance> performances) {
		this.performances = performances;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Movie)) {
			return false;
		}
		Movie other = (Movie) obj;
		return this.genre.getId() == other.genre.getId() && this.title.equals(other.title)
				&& this.year == other.year;
	}

}
