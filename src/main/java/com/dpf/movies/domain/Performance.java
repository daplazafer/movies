package com.dpf.movies.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dpf.movies.core.base.BaseEntity;
import com.dpf.movies.domain.pk.PerformancePK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERFORMANCES")
public class Performance extends BaseEntity {

	@EmbeddedId
	private PerformancePK id;
	
}
