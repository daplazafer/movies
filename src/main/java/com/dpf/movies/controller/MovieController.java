package com.dpf.movies.controller;

import com.dpf.movies.dto.MovieDetailOutDTO;
import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.dto.MovieOutDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieController {

    ResponseEntity<MovieDetailOutDTO> create(MovieInDTO movie);

    ResponseEntity<List<MovieOutDTO>> readAll(Pageable pageable);

    ResponseEntity<MovieDetailOutDTO> read(Long id);

    ResponseEntity<MovieDetailOutDTO> update(Long id, MovieInDTO movie);

    ResponseEntity<MovieDetailOutDTO> partialUpdate(Long id, MovieInDTO movie);

    ResponseEntity delete(Long id);

}
