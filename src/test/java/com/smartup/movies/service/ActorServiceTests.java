package com.smartup.movies.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartup.movies.core.exception.NotFoundException;
import com.smartup.movies.dto.ActorDTO;
import com.smartup.movies.entity.Actor;
import com.smartup.movies.repository.ActorRepository;
import com.smartup.movies.service.impl.ActorServiceImpl;
import com.smartup.movies.test.compare.ActorComparer;
import com.smartup.movies.test.dummy.ActorDummyCreator;

@RunWith(SpringRunner.class)
public class ActorServiceTests {

	private ActorService actorService;

	@Mock
	private ActorRepository actorRepository;

	@Before
	public void setUp() {
		actorService = new ActorServiceImpl(actorRepository);
	}

	@Test
	public void testGetByIdSuccess() {
		// Given
		Actor actor = ActorDummyCreator.random();
		when(actorRepository.findById(any())).thenReturn(Optional.of(actor));

		// When
		ActorDTO actorDTO = actorService.getById(actor.getId());

		// Then
		verify(actorRepository, times(1)).findById(actor.getId());
		assertTrue("Retrieved actor does not match with saved one",
				ActorComparer.compareActorToActorDTO(actor, actorDTO));
	}

	@Test(expected = NotFoundException.class)
	public void testGetByIdNotFound() {
		// Given
		Long id = 99l;
		when(actorRepository.findById(id)).thenReturn(Optional.empty());

		// When
		actorService.getById(id);

		// Then
		fail("NotFoundException has not been thrown when empty actor is retrieved by repository");
	}

}
