package com.smartup.movies.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.smartup.movies.core.base.BaseRequestTimeInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("requestTimeInterceptor")
	private BaseRequestTimeInterceptor requestTimeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestTimeInterceptor);
	}

}
