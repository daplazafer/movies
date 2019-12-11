package com.dpf.movies.core.configuration;

import com.dpf.movies.core.util.Hasher;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MapperConfiguration {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Bean
    @Autowired
    MapperFacade getMapperFacade(List<CustomConverter> customConverters) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        customConverters.stream()
                .peek(converter -> logger.info(String.format("Registered converter: %s -> %s", converter.getAType(), converter.getBType())))
                .forEach(customConverter -> mapperFactory.getConverterFactory().registerConverter(customConverter));
        return mapperFactory.getMapperFacade();
    }

    @Bean
    Hasher getHasher(){
        return new Hasher();
    }

}
