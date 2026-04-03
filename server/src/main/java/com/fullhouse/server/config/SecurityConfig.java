package com.fullhouse.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/google/oauth/**", "/survey/apply", "/survey/getlist", "/parent-survey/create", "/parent-survey/get-list", "/parent-survey/getlist/name-category-search", "/loginSuccess/client", "/business/getlist/name-search", "/business/getlist/category-city-search", "/business/getlist/survey", "/business/getlist/owner", "/business/claim/**", "/business/*/logo", "/logos/**", "/verification/generate", "/logout/client").permitAll()
                        .requestMatchers("/", "/index.html", "/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/loginSuccess", true)
                );

        return http.build();
    }
}