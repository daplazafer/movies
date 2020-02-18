package com.dpf.movies.common.constants;

public final class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/authenticate";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
    public static final String TOKEN_CLAIM_ROLES = "roles";
    public static final int TOKEN_EXPIRATION_MINUTES = 20;

}