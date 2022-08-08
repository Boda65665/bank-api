package com.example.demo.sequriti;
import com.example.demo.API.ApiKey;
import com.example.demo.JWT.JwtAuthenticationException;
import com.example.demo.JWT.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SequritiFilter extends GenericFilterBean {
    private ApiKey apikey;
    private final JwtTokenProvider jwtTokenProvider;
    public SequritiFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

            try {
                if (token != null && jwtTokenProvider.validateToken(token)) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    if (authentication != null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (JwtAuthenticationException e) {
                SecurityContextHolder.clearContext();
                ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
                try {
                    throw new JwtAuthenticationException("JWT token is expired or invalid");
                } catch (JwtAuthenticationException ex) {
                    ex.printStackTrace();
                }
            }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}