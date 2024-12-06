package com.openclassrooms.chatop.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
  private Integer id;
  private String message;
  private Integer rental_id;
  private Integer user_id;
}
