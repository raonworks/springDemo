package com.example.demo.services;

import com.example.demo.models.dto.DtoAuthResponse;
import com.example.demo.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public DtoAuthResponse authenticate(User user) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
    );
    return new DtoAuthResponse(jwtService.generateToken(user));
  }

}
