package com.smartup.movies.core.batch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.smartup.movies.core.base.BaseRequestTimeInterceptor;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends BaseRequestTimeInterceptor {
	
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
		throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
		throws Exception {
		long startTime = (long) request.getAttribute("startTime");		
		LOG.info("(" + request.getRemoteHost()+") "+request.getMethod()+" " + request.getRequestURL().toString() + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}
}
