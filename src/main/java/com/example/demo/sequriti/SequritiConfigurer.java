package com.example.demo.sequriti;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SequritiConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final SequritiFilter sequritiFilter;

    public SequritiConfigurer(SequritiFilter sequritiFilter) {
        this.sequritiFilter = sequritiFilter;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) {
        httpSecurity.addFilterBefore(sequritiFilter, UsernamePasswordAuthenticationFilter.class);

    }
}