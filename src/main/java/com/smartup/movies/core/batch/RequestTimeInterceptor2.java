package com.smartup.movies.core.batch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.smartup.movies.core.base.BaseRequestTimeInterceptor;

@Component("requestTimeInterceptor2")
public class RequestTimeInterceptor2 extends BaseRequestTimeInterceptor {
	
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor2.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
		throws Exception {
		LOG.info(request.getMethod());
		return true;
	}

}
