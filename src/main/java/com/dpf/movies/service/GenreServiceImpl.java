package com.dpf.movies.service;

import com.dpf.movies.common.base.BaseService;
import com.dpf.movies.common.exception.NotFoundException;
import com.dpf.movies.domain.Genre;
import com.dpf.movies.dto.GenreOutDTO;
import com.dpf.movies.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl extends BaseService implements GenreService {

    private GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        super();
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreOutDTO getById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Genre not found with id=" + id.toString()));
        return getMapper().map(genre, GenreOutDTO.class);
    }

}
