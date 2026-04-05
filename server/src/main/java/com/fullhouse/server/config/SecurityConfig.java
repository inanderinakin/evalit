package com.fullhouse.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/google/oauth/**", "/survey/apply", "/survey/getlist", "/parent-survey/create", "/parent-survey/get-list", "/parent-survey/getlist/name-category-search", "/loginSuccess/client", "/business/getlist/name-search", "/business/getlist/category-city-search", "/business/getlist/survey", "/business/getlist/owner", "/business/claim/**", "/business/*/logo", "/logos/**", "/verification/generate", "/parent-survey/get-singular", "/logout/client", "/admin/*", "/parent-survey/get-list/reported", "/parent-survey/report", "/api/User/**").permitAll()
                        .requestMatchers("/", "/index.html", "/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/loginSuccess", true)
                );

        return http.build();
    }
}