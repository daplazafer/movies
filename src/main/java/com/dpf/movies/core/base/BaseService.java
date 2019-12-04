package com.dpf.movies.core.base;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:error.properties")
public abstract class BaseService {

    @Autowired
    private MapperFacade mapper;

    protected MapperFacade getMapper() {
        return mapper;
    }

}
