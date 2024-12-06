package com.openclassrooms.chatop.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageDTO {
  private int rental_id;
  private int user_id;
  private String message;
}
