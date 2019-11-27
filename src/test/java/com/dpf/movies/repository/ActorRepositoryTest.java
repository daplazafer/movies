package com.dpf.movies.repository;

import com.dpf.movies.domain.Actor;
import com.dpf.movies.domain.Genre;
import com.dpf.movies.domain.Movie;
import com.dpf.movies.util.DummyEntityGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ActorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ActorRepository underTest;

    @Test
    public void findByMovieIdNameQuery() {
        //GIVEN
        Genre genre = entityManager.persistAndFlush(DummyEntityGenerator.createGenre());
        Movie movie = entityManager.persistAndFlush(DummyEntityGenerator.createMovie(genre));
        Actor actor = entityManager.persistAndFlush(DummyEntityGenerator.createActor());
        entityManager.persistAndFlush(DummyEntityGenerator.createPerformance(movie, actor));

        //WHEN
        List<Actor> result = underTest.findByPerformances_Id_Movie_Id(movie.getId());

        //THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(actor.getName(), result.get(0).getName());
    }

    @Test
    public void findByMovieIdQueryDSL() {
        //GIVEN
        Genre genre = entityManager.persistAndFlush(DummyEntityGenerator.createGenre());
        Movie movie = entityManager.persistAndFlush(DummyEntityGenerator.createMovie(genre));
        Actor actor = entityManager.persistAndFlush(DummyEntityGenerator.createActor());
        entityManager.persistAndFlush(DummyEntityGenerator.createPerformance(movie, actor));

        //WHEN
        List<Actor> result = underTest.findByMovieIdQueryDSL(movie.getId());

        //THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(actor.getName(), result.get(0).getName());
    }

    @Test
    public void findByMovieIdQueryJPQL() {
        //GIVEN
        Genre genre = entityManager.persistAndFlush(DummyEntityGenerator.createGenre());
        Movie movie = entityManager.persistAndFlush(DummyEntityGenerator.createMovie(genre));
        Actor actor = entityManager.persistAndFlush(DummyEntityGenerator.createActor());
        entityManager.persistAndFlush(DummyEntityGenerator.createPerformance(movie, actor));

        //WHEN
        List<Actor> result = underTest.findByMovieIdQuery(movie.getId());

        //THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(actor.getName(), result.get(0).getName());
    }

}