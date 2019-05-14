package com.smartup.movies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.smartup.movies.core.exception.NotFoundException;
import com.smartup.movies.dto.ActorDTO;
import com.smartup.movies.dto.mapper.ActorMapper;
import com.smartup.movies.entity.Actor;
import com.smartup.movies.repository.ActorRepository;
import com.smartup.movies.service.ActorService;

@Service
@PropertySource("classpath:error.properties")
public class ActorServiceImpl implements ActorService {

	private ActorRepository actorRepository;
	
	private @Value("${error.actor.notfound}") String ERROR_MESSAGE;

	@Autowired
	public ActorServiceImpl(ActorRepository actorRepository) {
		super();
		this.actorRepository = actorRepository;
	}
	
	@Override
	public ActorDTO getById(Long id) {
		Actor actor = actorRepository.findById(id).orElseThrow(() -> new NotFoundException(ERROR_MESSAGE + id.toString()));
		return ActorMapper.mapToActorDTO(actor);
	}	

}
