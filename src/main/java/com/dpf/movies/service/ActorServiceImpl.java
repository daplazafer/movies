package com.dpf.movies.service;

import com.dpf.movies.common.base.BaseService;
import com.dpf.movies.common.exception.NotFoundException;
import com.dpf.movies.domain.Actor;
import com.dpf.movies.dto.ActorOutDTO;
import com.dpf.movies.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl extends BaseService implements ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        super();
        this.actorRepository = actorRepository;
    }

    @Override
    public ActorOutDTO getById(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Actor not found with id=" + id.toString()));
        return getMapper().map(actor, ActorOutDTO.class);
    }

}
