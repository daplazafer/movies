package com.dpf.movies.dto.mapper;

import com.dpf.movies.domain.Movie;
import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.util.DummyDTOGenerator;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class MovieInDTOToMovieConverterTest {

    private MapperFacade underTest;

    @Before
    public void setUp(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new MovieInDTOToMovieConverter());
        underTest = mapperFactory.getMapperFacade();
    }

    @Test
    public void givenMovieInDTOReturnMovie(){
        //GIVEN
        MovieInDTO source = DummyDTOGenerator.createMovieInDTO();

        //WHEN
        Movie result = underTest.map(source, Movie.class);

        //THEN
        assertNotNull(result);
        assertEquals(source.getTitle(), result.getTitle());
        assertTrue(source.getYear() == result.getYear());
    }

}