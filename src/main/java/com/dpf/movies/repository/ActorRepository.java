package com.dpf.movies.repository;

import com.dpf.movies.domain.Actor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends CrudRepository<Actor, Long>{

    @Query("SELECT a FROM Actor a INNER JOIN Performance p ON a = p.id.actor INNER JOIN Movie m ON p.id.movie = m AND m.id = :id")
    List<Actor> findByMovieId(@Param("id") Long id);

}
