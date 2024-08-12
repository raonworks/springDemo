package com.example.demo.controllers;

import com.example.demo.models.dto.DtoAuthRequest;
import com.example.demo.models.dto.DtoAuthResponse;
import com.example.demo.models.User;
import com.example.demo.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @GetMapping("/foo")
  public String Foo(@RequestParam String name) {
    //arrow, inline 사용법
    Loan loan = switch (name) {
      case "foo" -> new SecureLonan();
      default -> new UnSecureLoan(0.3f);
    };

    //jdk 21 이상, pattern matching
    String message = switch (loan) {
      case SecureLonan sl -> name + "님은 무이자";
//      case UnSecureLoan usl -> name + "님은 " + usl.interest;
      case UnSecureLoan(float interest) -> name + "님은 " + interest;
    };

    return message;
  }

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

  sealed interface Loan permits SecureLonan, UnSecureLoan {

  }

  final class SecureLonan implements Loan {

  }

  record UnSecureLoan(float interest) implements Loan {

  }

}
