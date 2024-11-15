package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User register(String email, String name, String password) {
    User user = new User();
    user.setEmail(email);
    user.setName(name);
    user.setPassword(passwordEncoder.encode(password));
    return userRepository.save(user);
  }
}
