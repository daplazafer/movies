package com.smartup.movies.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.smartup.movies.entity.Movie;
import com.smartup.movies.entity.Performance;
import com.smartup.movies.entity.pk.PerformancePK;

public interface PerformanceRepository extends PagingAndSortingRepository<Performance, PerformancePK>{
	
	public Page<Performance> findById_Movie(Pageable pageable, Movie movie);

}
