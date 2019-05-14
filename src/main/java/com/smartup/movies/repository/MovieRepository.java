package com.smartup.movies.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.smartup.movies.entity.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>{

}
