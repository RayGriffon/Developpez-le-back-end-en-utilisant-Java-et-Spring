package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.JWTService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  JWTService jwtService;

  public LoginController (JWTService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public String getToken(Authentication authentication) {
    String token = jwtService.generateToken(authentication);
    return token;
  }

  @GetMapping("/user")
  public String getUser() {
    return "Welcome, User";
  }

  @GetMapping("/admin")
  public String getAdmin() {
    return "Welcome, Admin";
  }
}
