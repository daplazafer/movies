package com.dpf.movies.core.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LogManager.getLogger(this.getClass().getName());

    @Pointcut("within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    private void controllerAndService() {
    }

    @Pointcut("within(@com.dpf.movies.core.log.Loggable *) || @annotation(com.dpf.movies.core.log.Loggable)")
    private void loggable() {
    }

    @Before("controllerAndService() || loggable()")
    private void logMethodInvocation(JoinPoint joinPoint) {
        logger.info(String.format("INVOKE %s", getMethodInvocationInfo(joinPoint)));
    }

    @Around("controllerAndService() || loggable()")
    private Object logTimeElapsedInMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long timeElapsed = System.currentTimeMillis();
        Object methodExecution = joinPoint.proceed();
        timeElapsed = System.currentTimeMillis() - timeElapsed;

        logger.info(String.format("COMPLETED %s in %s ms",
                getMethodInvocationInfo(joinPoint),
                timeElapsed));
        return methodExecution;
    }

    private static String getMethodInvocationInfo(JoinPoint joinPoint) {
        return String.format("%s.%s(%s)",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Optional.ofNullable(joinPoint.getArgs()).map(args -> Stream.of(args)
                        .map(Objects::toString)
                        .collect(Collectors.joining(", "))).orElse(Strings.EMPTY));
    }

}
