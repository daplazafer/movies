package com.dpf.movies.core.base;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    private MapperFacade mapper;

    protected MapperFacade getMapper(){
        return mapper;
    }

}
