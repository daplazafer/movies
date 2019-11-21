package com.dpf.movies.repository;

import com.dpf.movies.domain.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {

}
