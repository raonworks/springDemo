package com.example.demo.controllers;

import com.example.demo.models.dto.DtoAuthRequest;
import com.example.demo.models.dto.DtoAuthResponse;
import com.example.demo.models.User;
import com.example.demo.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoAuthRequest login) {
    User user = User.builder()
            .email(login.email())
            .password(login.password())
            .build();

    return ResponseEntity.ok(authService.authenticate(user));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(HttpServletRequest req, HttpServletResponse res) {
    authService.refreshToken(req, res);
  }

}
