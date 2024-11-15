package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  public List<Rental> getAllRentals() {
    return rentalRepository.findAll();
  }

  public Rental getRentalById(Integer id) {
    return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
  }

  public Rental createRental(Rental rental) {
    return rentalRepository.save(rental);
  }

  public Rental updateRental(Rental rental) {
    return rentalRepository.save(rental);
  }
}
