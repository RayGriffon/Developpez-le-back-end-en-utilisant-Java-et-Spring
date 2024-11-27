package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.service.dto.UserDTO;
import com.openclassrooms.chatop.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  public User findById(Integer id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User non trouvé"));
  }

  public UserDTO getUserById(Integer id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("User not found"));
    return userMapper.toUserDTO(user);
  }

}
