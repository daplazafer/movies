package com.dpf.movies.domain;

import com.dpf.movies.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIES")
public class Movie extends BaseEntity {
	
	public static final int YEAR_MIN = 1800;
	public static final int YEAR_MAX = 2200;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String title;

	@ManyToOne
	@JoinColumn(name = "genre_id")
	private Genre genre;

	@Min(YEAR_MIN)
	@Max(YEAR_MAX)
	private int year;

	@OneToMany(mappedBy = "id.movie", cascade = CascadeType.ALL)
	private List<Performance> performances;

}
