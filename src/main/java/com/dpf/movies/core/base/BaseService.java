package com.dpf.movies.core.base;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

public abstract class BaseService {

    @Autowired(required = true)
    private MapperFacade mapper;

    protected MapperFacade getMapper() {
        return mapper;
    }

}
