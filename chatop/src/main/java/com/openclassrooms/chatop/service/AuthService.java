package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.service.dto.UserDTO;
import com.openclassrooms.chatop.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public String register(UserDTO userDTO) {
    if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
      throw new IllegalArgumentException("Email déjà utilisé");
    }
    User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setName(userDTO.getName());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    userRepository.save(user);
    return jwtService.generateToken(new UsernamePasswordAuthenticationToken(user.getEmail(), null));
  }

  public String login(String email, String password) {
    User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new IllegalArgumentException("User non trouvé"));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("Invalid credentials");
    }
    return jwtService.generateToken(new UsernamePasswordAuthenticationToken(email, null));
  }

  public UserDTO getMe(Authentication authentication) {
    User user = userRepository.findByEmail(authentication.getName())
      .orElseThrow(() -> new IllegalArgumentException("User non trouvé"));
    return new UserMapper().toUserDTO(user);
  }
}
