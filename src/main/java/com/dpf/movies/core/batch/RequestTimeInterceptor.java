package com.dpf.movies.core.batch;

import com.dpf.movies.core.log.Loggable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(RequestTimeInterceptor.class);

    @Loggable
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (long) request.getAttribute("startTime");
        long timeElapsed = System.currentTimeMillis() - startTime;
        logger.info(String.format("%s %s(%s) %s in %s ms",
                request.getMethod(),
                Optional.ofNullable(request.getRemoteUser()).orElse("unknown"),
                request.getRemoteAddr(),
                request.getRequestURL(),
                timeElapsed));
    }

}
