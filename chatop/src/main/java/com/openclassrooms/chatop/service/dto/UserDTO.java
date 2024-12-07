package com.openclassrooms.chatop.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {
  private Integer id;
  private String email;
  private String name;
  private String password;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
}
