package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.JWTService;
import com.openclassrooms.chatop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public User register(@RequestBody Map<String, String> payload) {
    return userService.register(payload.get("email"), payload.get("name"), payload.get("password"));
  }

  @PostMapping("/login")
  public String login(@RequestBody Map<String, String> payload) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(payload.get("login"), payload.get("password")));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return jwtService.generateToken(authentication);
  }

  @GetMapping("/me")
  public User me(Authentication authentication) {
    return (User) authentication.getPrincipal();
  }
}
