package com.example.javaeeproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers("/books/add").hasRole("ADMIN")
                    .requestMatchers("/books/*/edit").hasRole("ADMIN")
                    .requestMatchers("/books/*/delete").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
                    .requestMatchers("/api/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
            .logout(LogoutConfigurer::permitAll)
            .exceptionHandling(configurer ->
                configurer.accessDeniedPage("/access-denied.html")
            );

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}