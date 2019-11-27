package com.dpf.movies.util;

import com.dpf.movies.domain.Actor;
import com.dpf.movies.domain.Genre;
import com.dpf.movies.domain.Movie;
import com.dpf.movies.domain.Performance;
import com.dpf.movies.domain.pk.PerformancePK;

public class DummyEntityGenerator {

    private DummyEntityGenerator() {
    }

    public static Movie createMovie(Genre genre) {
        return Movie.builder()
                .title(Random.text())
                .year(Random.number(1990,2020))
                .genre(genre)
                .build();
    }

    public static Genre createGenre() {
        return Genre.builder()
                .name(Random.text())
                .build();
    }

    public static Actor createActor() {
        return Actor.builder()
                .name(Random.text())
                .build();
    }

    public static Performance createPerformance(Movie movie, Actor actor){
        return Performance.builder()
                .id(PerformancePK.builder()
                        .movie(movie)
                        .actor(actor)
                        .build())
                .build();
    }

}
