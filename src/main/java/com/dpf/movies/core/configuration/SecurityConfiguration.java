package com.dpf.movies.core.configuration;

import com.dpf.movies.core.constants.SecurityConstants;
import com.dpf.movies.core.security.JwtAuthenticationFilter;
import com.dpf.movies.core.security.JwtAuthorizationFilter;
import com.dpf.movies.core.security.UserRepositoryAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepositoryAuthenticationProvider authenticationProvider;

    private @Value("${v1API}")
    String v1API;

    private @Value("${resource.movies}")
    String resourceMovies;

    private @Value("${resource.user.me}")
    String resourceUserMe;

    private @Value("${jwtSecret}")
    String jwtSecret;

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/api/h2-console/**").permitAll()
                .antMatchers("/api" + SecurityConstants.AUTH_LOGIN_URL).permitAll()
                .antMatchers("/api" + resourceUserMe).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtSecret))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtSecret))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
