package com.smartup.movies.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.smartup.movies.dto.PerformanceActorDTO;
import com.smartup.movies.dto.PerformanceDTO;

public interface PerformanceService {

	public PerformanceDTO save(PerformanceDTO performance);

	public List<PerformanceActorDTO> getPerformancesByMovieId(Pageable pageable, Long movieId);

}
