package com.dpf.movies.core.base;

import ma.glasnost.orika.MapperFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    private MapperFacade mapper;

    private Logger logger = LogManager.getLogger(this.getClass().getName());


    protected Logger getLogger(){
        return logger;
    }

    protected MapperFacade getMapper(){
        return mapper;
    }

}
