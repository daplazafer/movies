package com.dpf.movies.repository;

import com.dpf.movies.domain.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>{

}
