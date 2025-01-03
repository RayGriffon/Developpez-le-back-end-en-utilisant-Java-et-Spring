package com.openclassrooms.chatop.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {
  private Integer id;
  private String name;
  private BigDecimal surface;
  private BigDecimal price;
  private String picture; // URL de l'image
  private String description;
  private Integer ownerId;
}
