package com.example.demo.services;

import com.example.demo.models.Token;
import com.example.demo.repositories.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;

        if(null == authHeader || !authHeader.startsWith("Bearer ")) return;

        jwt = authHeader.substring(7);
        Token token = tokenRepository.findByToken(jwt).orElse(null);
        if(null != token) {
            token.setExpired(true);
            token.setRevoked(true);
            tokenRepository.save(token);
        }

    }
}
