package com.smartup.movies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.smartup.movies.core.exception.NotFoundException;
import com.smartup.movies.dto.GenreDTO;
import com.smartup.movies.dto.mapper.GenreMapper;
import com.smartup.movies.entity.Genre;
import com.smartup.movies.repository.GenreRepository;
import com.smartup.movies.service.GenreService;

@Service
@PropertySource("classpath:error.properties")
public class GenreServiceImpl implements GenreService {
	
	private GenreRepository genreRepository;
	
	private @Value("${error.genre.notfound}") String ERROR_MESSAGE;
	
	@Autowired
	public GenreServiceImpl(GenreRepository genreRepository) {
		super();
		this.genreRepository = genreRepository;
	}

	@Override
	public GenreDTO getById(Long id) {
		Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException(ERROR_MESSAGE + id.toString()));
		return GenreMapper.mapToGenreDTO(genre);
	}

}
