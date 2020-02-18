package com.dpf.movies.service;

import com.dpf.movies.dto.MovieDetailOutDTO;
import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.dto.MovieOutDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    List<MovieOutDTO> getAll(Pageable pageable);

    MovieDetailOutDTO getById(Long id);

    MovieDetailOutDTO save(MovieInDTO movie);

    MovieDetailOutDTO update(Long id, MovieInDTO movie);

    MovieDetailOutDTO partialUpdate(Long id, MovieInDTO movie);

    void delete(Long id);

}
