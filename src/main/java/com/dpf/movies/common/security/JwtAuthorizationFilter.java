package com.dpf.movies.common.security;

import com.dpf.movies.common.constants.SecurityConstants;
import com.dpf.movies.common.exception.JwtMatchingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final String jwtSecret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, String jwtSecret) {
        super(authenticationManager);
        this.jwtSecret = jwtSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {

                Jws<Claims> parsedToken = Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));

                String username = parsedToken
                    .getBody()
                    .getSubject();

                List<GrantedAuthority> authorities = ((List<String>) parsedToken.getBody()
                    .get(SecurityConstants.TOKEN_CLAIM_ROLES)).stream()
                    .map(authority -> new SimpleGrantedAuthority(authority))
                    .collect(Collectors.toList());

                if (StringUtils.isNotEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            } catch (Exception ex) {
                throw new JwtMatchingException("JWT token invalid or expired");
            }
        }
        return null;
    }
}