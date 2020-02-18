package com.dpf.movies.controller;

import com.dpf.movies.dto.MovieDetailOutDTO;
import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.dto.MovieOutDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MovieController {

    MovieDetailOutDTO create(MovieInDTO movie);

    List<MovieOutDTO> readAll(Pageable pageable);

    MovieDetailOutDTO read(Long id);

    MovieDetailOutDTO update(Long id, MovieInDTO movie);

    MovieDetailOutDTO partialUpdate(Long id, MovieInDTO movie);

    void delete(Long id);

}
