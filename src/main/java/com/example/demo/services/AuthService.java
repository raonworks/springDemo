package com.example.demo.services;

import com.example.demo.models.Token;
import com.example.demo.models.TokenType;
import com.example.demo.models.dto.DtoAuthResponse;
import com.example.demo.models.User;
import com.example.demo.repositories.TokenRepository;
import com.example.demo.repositories.TokenRepositoryCustom;
import com.example.demo.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public DtoAuthResponse authenticate(User user) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
    );

    String accessToken = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);

    revokeAllUserTokens(user);
    saveToken(user, accessToken);

    return new DtoAuthResponse(accessToken, refreshToken);
  }

  private void revokeAllUserTokens(User user) {
    List<Token> validTokens = tokenRepository.findAllValidTokenByUser(user.getEmail());
    if (!validTokens.isEmpty()) {
      validTokens.forEach( t-> {
        t.setExpired(true);
        t.setRevoked(true);
      });
      tokenRepository.saveAll(validTokens);
    }
  }

  private void saveToken (User user, String accessToken) {
    Token token = Token.builder()
            .token(accessToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .email(user.getUsername())
            .build();
    tokenRepository.save(token);
  }

  public void refreshToken(HttpServletRequest req, HttpServletResponse res) {
    final String authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;

    if (null == authHeader || !authHeader.startsWith("Bearer ")) return;

    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (null != userEmail) {
      User user = userRepository.findByEmail(userEmail).orElse(null);
      if (null == user) return;

      if (jwtService.isTokenValid(refreshToken, user)) {
        String accessToken = jwtService.generateToken(user);
        DtoAuthResponse authResponse = new DtoAuthResponse(accessToken, refreshToken);
        try {
          new ObjectMapper().writeValue(res.getOutputStream(), authResponse);
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
    }

  }

}
