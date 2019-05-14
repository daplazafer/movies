package com.smartup.movies.repository;

import org.springframework.data.repository.CrudRepository;

import com.smartup.movies.entity.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
