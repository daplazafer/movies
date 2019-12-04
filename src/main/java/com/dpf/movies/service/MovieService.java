package com.dpf.movies.service;

import com.dpf.movies.dto.MovieDetailOutDTO;
import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.dto.MovieOutDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    List<MovieOutDTO> getAll(Pageable pageable);

    MovieDetailOutDTO getById(Long id);

    MovieDetailOutDTO save(MovieInDTO movie);

    MovieDetailOutDTO update(Long id, MovieInDTO movie);

    MovieDetailOutDTO partialUpdate(Long id, MovieInDTO movie);

    void delete(Long id);

}
