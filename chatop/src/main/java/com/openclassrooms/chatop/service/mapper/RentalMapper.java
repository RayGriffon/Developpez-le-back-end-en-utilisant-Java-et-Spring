package com.openclassrooms.chatop.service.mapper;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.UserService;
import com.openclassrooms.chatop.service.dto.RentalDTO;
import com.openclassrooms.chatop.service.dto.RentalFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {

  @Autowired
  private UserService userService;

  public RentalDTO toRentalDTO(Rental rental) {
    RentalDTO rentalDTO = new RentalDTO();
    rentalDTO.setId(rental.getId());
    rentalDTO.setOwnerId(rental.getOwner().getId());
    rentalDTO.setName(rental.getName());
    rentalDTO.setDescription(rental.getDescription());
    rentalDTO.setSurface(rental.getSurface());
    rentalDTO.setPrice(rental.getPrice());
    rentalDTO.setPicture(rental.getPicture());
    return rentalDTO;
  }

  public Rental toRental(RentalFormDTO rentalDTO, String url) throws IOException {
    Rental rental = new Rental();
    rental.setOwner(userService.findById(rentalDTO.getOwnerId()));
    rental.setName(rentalDTO.getName());
    rental.setDescription(rentalDTO.getDescription());
    rental.setSurface(rentalDTO.getSurface());
    rental.setPrice(rentalDTO.getPrice());
    rental.setPicture(url);
    return rental;
  }

  public Rental toRental(RentalDTO rentalDTO) {
    Rental rental = new Rental();
    rental.setOwner(userService.findById(rentalDTO.getOwnerId()));
    rental.setName(rentalDTO.getName());
    rental.setDescription(rentalDTO.getDescription());
    rental.setSurface(rentalDTO.getSurface());
    rental.setPrice(rentalDTO.getPrice());
    return rental;
  }

  public List<RentalDTO> toListRentalDTO(List<Rental> rentals) {
    if (rentals == null || rentals.isEmpty()) {return null;}

    return rentals.stream()
      .map(this::toRentalDTO).collect(Collectors.toList());
  }
}
