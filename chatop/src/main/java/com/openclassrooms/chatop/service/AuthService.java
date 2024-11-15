package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

}
