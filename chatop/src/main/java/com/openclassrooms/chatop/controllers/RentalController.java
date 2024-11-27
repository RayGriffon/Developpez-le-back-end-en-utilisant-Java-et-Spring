package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.RentalService;
import com.openclassrooms.chatop.service.dto.RentalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @GetMapping
  public ResponseEntity<Map<String, List<RentalDTO>>> getAllRentals() {
    return ResponseEntity.ok(Map.of("rentals", rentalService.getAllRentals()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RentalDTO> getRentalById(@PathVariable Integer id) {
    return ResponseEntity.ok(rentalService.getRentalById(id));
  }

  @PostMapping
  public ResponseEntity<Map<String, String>> createRental(@RequestBody RentalDTO rentalDTO) {
    rentalService.createRental(rentalDTO);
    return ResponseEntity.ok(Map.of("message", "Rental created!"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, String>> updateRental(@PathVariable Integer id, @RequestBody RentalDTO rentalDTO) {
    rentalService.updateRental(id, rentalDTO);
    return ResponseEntity.ok(Map.of("message", "Rental updated!"));
  }
}
