package com.openclassrooms.chatop.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateRentalDTO {

  private String name;

  private String description;

  private BigDecimal surface;

  private BigDecimal price;
}
