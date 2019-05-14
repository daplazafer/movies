package com.smartup.movies.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartup.movies.core.exception.NotFoundException;
import com.smartup.movies.dto.GenreDTO;
import com.smartup.movies.entity.Genre;
import com.smartup.movies.repository.GenreRepository;
import com.smartup.movies.service.impl.GenreServiceImpl;
import com.smartup.movies.test.compare.GenreComparer;
import com.smartup.movies.test.dummy.GenreDummyCreator;

@RunWith(SpringRunner.class)
public class GenreServiceTests {

	private GenreService genreService;

	@Mock
	private GenreRepository genreRepository;

	@Before
	public void setUp() {
		genreService = new GenreServiceImpl(genreRepository);
	}

	@Test
	public void testGetByIdSuccess() {
		// Given
		Genre genre = GenreDummyCreator.random();
		when(genreRepository.findById(genre.getId())).thenReturn(Optional.of(genre));

		// When
		GenreDTO genreDTO = genreService.getById(genre.getId());

		// Then
		verify(genreRepository, times(1)).findById(genre.getId());
		assertTrue("Retrieved genre does not match with saved one",
				GenreComparer.compareGenreToGenreDTO(genre, genreDTO));
	}

	@Test(expected = NotFoundException.class)
	public void testGetByIdNotFound() {
		// Given
		Long id = 99l;
		when(genreRepository.findById(id)).thenReturn(Optional.empty());

		// When
		genreService.getById(id);

		// Then
		fail("NotFoundException has not been thrown when empty genre is retrieved by repository");
	}

}
