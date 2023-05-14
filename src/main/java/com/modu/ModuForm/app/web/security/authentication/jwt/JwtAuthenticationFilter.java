package com.modu.ModuForm.app.web.security.authentication.jwt;

import com.modu.ModuForm.app.exception.auth.AuthenticationExpireException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final CustomJwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (jwtProvider.validToken(request, response)) {
            throw new AuthenticationExpireException();
        }

        jwtProvider.saveAuthentication(request);
    }
}
