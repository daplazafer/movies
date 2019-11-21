package com.dpf.movies.core.configuration;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MapperConfiguration {

    @Bean
    @Autowired
    MapperFacade getMapperFacade(List<CustomConverter> customConverters) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        customConverters.forEach(customConverter -> mapperFactory.getConverterFactory().registerConverter(customConverter));
        return mapperFactory.getMapperFacade();
    }

}
