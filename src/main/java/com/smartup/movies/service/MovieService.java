package com.smartup.movies.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.smartup.movies.dto.MovieDTO;
import com.smartup.movies.dto.MovieListAllDTO;
import com.smartup.movies.dto.MovieSaveDTO;

public interface MovieService {
		
	public List<MovieListAllDTO> getAll(Pageable pageable);
	
	public MovieDTO getById(Long id);
	
	public MovieDTO save(MovieSaveDTO movie);

}
