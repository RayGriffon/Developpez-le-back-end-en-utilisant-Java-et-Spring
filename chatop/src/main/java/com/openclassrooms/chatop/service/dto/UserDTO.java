package com.openclassrooms.chatop.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Integer id;
  private String email;
  private String name;
  private String password;
}
