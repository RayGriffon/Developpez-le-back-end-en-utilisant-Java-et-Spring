package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.exceptions.BadRequestException;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.service.dto.UserDTO;
import com.openclassrooms.chatop.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  public User findById(Integer id) {
    return userRepository.findById(id).orElseThrow(() -> new BadRequestException("User non trouvé"));
  }

  public UserDTO getUserById(Integer id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new BadRequestException("User non trouvé"));
    return userMapper.toUserDTO(user);
  }

  public UserDTO findByMail(String mail) {
    User user = userRepository.findByEmail(mail).orElseThrow(() -> new BadRequestException("Email non trouvé"));
    return userMapper.toUserDTO(user);
  }

  public UserDTO findById(int id) {
    User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("User non trouvé"));
    return userMapper.toUserDTO(user);
  }

}
