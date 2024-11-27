package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.service.dto.RentalDTO;
import com.openclassrooms.chatop.service.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private RentalMapper rentalMapper;

  public Rental findById(Integer id) {
    return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental non trouvé"));
  }

  public List<RentalDTO> getAllRentals() {
    return rentalMapper.toListRentalDTO(rentalRepository.findAll());
  }

  public RentalDTO getRentalById(Integer id) {
    Rental rental = rentalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rental non trouvé"));
    return rentalMapper.toRentalDTO(rental);
  }

  public void createRental(RentalDTO rentalDTO) {
    rentalRepository.save(rentalMapper.toRental(rentalDTO));
  }

  public void updateRental(Integer id, RentalDTO rentalDTO) {
    Rental rental = rentalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rental non trouvé"));
    rental.setName(rentalDTO.getName());
    rental.setDescription(rentalDTO.getDescription());
    rental.setSurface(rentalDTO.getSurface());
    rental.setPrice(rentalDTO.getPrice());
    rental.setPicture(rentalDTO.getPicture());
    rentalRepository.save(rental);
  }

  private String saveImage(MultipartFile image) throws IOException {
    String folder = "uploads/";
    String filePath = folder + image.getOriginalFilename();
    File dest = new File(filePath);
    image.transferTo(dest);
    return "/images" + image.getOriginalFilename();
  }

}
