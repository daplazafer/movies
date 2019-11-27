package com.dpf.movies.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Random {

    private static final int RANDOM_STRING_SIZE = 8;
    private static final int LOWERCASE_LOWER_LIMIT = 97;
    private static final int LOWERCASE_UPPER_LIMIT = 122;

    private Random() {
    }

    static int number(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    static String text() {
        return IntStream.rangeClosed(0, RANDOM_STRING_SIZE)
                .map(i -> number(LOWERCASE_LOWER_LIMIT, LOWERCASE_UPPER_LIMIT))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    static Long id(){
        return ThreadLocalRandom.current().nextLong();
    }

}
