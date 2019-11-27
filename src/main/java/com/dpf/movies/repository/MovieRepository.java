package com.dpf.movies.repository;

import com.dpf.movies.domain.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

}
