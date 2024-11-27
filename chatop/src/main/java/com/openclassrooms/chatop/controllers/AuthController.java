package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.AuthService;
import com.openclassrooms.chatop.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO userDTO) {
    String token = authService.register(userDTO);
    return ResponseEntity.ok(Map.of("token", token));
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
    String token = authService.login(loginRequest.get("login"), loginRequest.get("password"));
    return ResponseEntity.ok(Map.of("token", token));
  }

  @GetMapping("/me")
  public ResponseEntity<UserDTO> me(Authentication authentication) {
    return ResponseEntity.ok(authService.getMe(authentication));
  }
}
