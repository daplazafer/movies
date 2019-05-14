package com.smartup.movies.repository;

import org.springframework.data.repository.CrudRepository;

import com.smartup.movies.entity.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long>{

}
