package com.dpf.movies.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    protected Logger getLogger(){
        return logger;
    }

}
