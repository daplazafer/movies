package com.dpf.movies.core.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)" +
            " || execution(public !void org.springframework.data.repository.Repository+.*(..))")
    private void controllerServiceRepository() {
    }

    @Pointcut("within(@com.dpf.movies.core.log.Loggable *) || @annotation(com.dpf.movies.core.log.Loggable)")
    private void loggable() {
    }

    @Pointcut("execution(* ma.glasnost.orika.MapperFacade.map(..))")
    private void mapper() {
    }

    @Before("controllerServiceRepository() || loggable()")
    private void logMethodInvocation(JoinPoint joinPoint) {
        log("ENTER %s", getMethodInvocationInfo(joinPoint));
    }

    @Around("controllerServiceRepository() || loggable()")
    private Object logTimeElapsedInMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long timeElapsed = System.currentTimeMillis();
        Object methodExecution = joinPoint.proceed();
        timeElapsed = System.currentTimeMillis() - timeElapsed;

        log("COMPLETED %s in %s ms",
                getMethodInvocationInfo(joinPoint),
                timeElapsed);
        return methodExecution;
    }

    @AfterReturning(value = "mapper()", returning = "returnValue")
    private void logMapping(JoinPoint joinPoint, Object returnValue) {
        log("MAP %s => %s", joinPoint.getArgs()[0], returnValue);
    }

    private static String getMethodInvocationInfo(JoinPoint joinPoint) {
        return String.format("%s.%s(%s)",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Optional.ofNullable(joinPoint.getArgs()).map(args -> Stream.of(args)
                        .map(Objects::toString)
                        .collect(Collectors.joining(", "))).orElse(Strings.EMPTY));
    }

    private void log(String message, Object... args) {
        log.info(String.format(message, args));
    }

}
