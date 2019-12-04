package com.dpf.movies.service.impl;

import com.dpf.movies.core.base.BaseService;
import com.dpf.movies.core.exception.NotFoundException;
import com.dpf.movies.domain.Genre;
import com.dpf.movies.dto.GenreOutDTO;
import com.dpf.movies.repository.GenreRepository;
import com.dpf.movies.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl extends BaseService implements GenreService {

    private GenreRepository genreRepository;

    private @Value("${error.genre.notfound}")
    String ERROR_MESSAGE;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        super();
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreOutDTO getById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException(ERROR_MESSAGE + id.toString()));
        return getMapper().map(genre, GenreOutDTO.class);
    }

}
