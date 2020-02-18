package com.dpf.movies.repository;

import com.dpf.movies.domain.Actor;
import com.dpf.movies.domain.QActor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends JpaRepository<Actor, Long>,
    QuerydslPredicateExecutor<Actor> {

    List<Actor> findByPerformances_Id_Movie_Id(Long movieId);

    @Query("SELECT a FROM Actor a INNER JOIN Performance p ON a = p.id.actor INNER JOIN Movie m ON p.id.movie = m AND m.id = :id")
    List<Actor> findByMovieIdQuery(@Param("id") Long movieId);

    default List<Actor> findByMovieIdQueryDSL(Long movieId) {
        Iterable<Actor> queryResult = findAll(
            QActor.actor.performances.any().id.movie.id.eq(movieId));
        return StreamSupport.stream(queryResult.spliterator(), false).collect(Collectors.toList());
    }

}
