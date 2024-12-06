package com.openclassrooms.chatop.service.mapper;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDTO toUserDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setName(user.getName());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }

  public User toUser(UserDTO userDTO) {
    User user = new User();
    user.setId(userDTO.getId());
    user.setName(userDTO.getName());
    user.setPassword(userDTO.getPassword());
    user.setEmail(userDTO.getEmail());
    return user;
  }
}
