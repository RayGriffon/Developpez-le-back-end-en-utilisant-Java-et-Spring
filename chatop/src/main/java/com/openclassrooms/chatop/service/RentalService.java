package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.service.dto.RentalDTO;
import com.openclassrooms.chatop.service.dto.RentalFormDTO;
import com.openclassrooms.chatop.service.dto.UpdateRentalDTO;
import com.openclassrooms.chatop.service.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class RentalService {

  @Value("${picture.upload}")
  private String path;

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private RentalMapper rentalMapper;

  public Rental findById(Integer id) {
    return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental non trouvé"));
  }

  public Rental findEntityById(int rentalId) {
    return rentalRepository.findById(rentalId)
      .orElseThrow(() -> new IllegalArgumentException("Rental non trouvé"));
  }


  public List<RentalDTO> getAllRentals() {
    return rentalMapper.toListRentalDTO(rentalRepository.findAll());
  }

  public RentalDTO getRentalById(Integer id) {
    Rental rental = rentalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rental non trouvé"));
    return rentalMapper.toRentalDTO(rental);
  }

  public void createRental(RentalFormDTO rentalDTO, MultipartFile multipartFile) throws IOException {
    String url = saveImage(multipartFile);
    rentalRepository.save(rentalMapper.toRental(rentalDTO, url));
  }

  public void updateRental(Integer id, UpdateRentalDTO updateRentalDTO, Integer userId) {

    Rental rental = rentalRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Rental non trouvé"));

    if (!rental.getOwner().getId().equals(userId)) {
      throw new SecurityException("Vous n'êtes pas autorisé à modifier ce rental");
    }

    if (updateRentalDTO.getName() != null) rental.setName(updateRentalDTO.getName());
    if (updateRentalDTO.getDescription() != null) rental.setDescription(updateRentalDTO.getDescription());
    if (updateRentalDTO.getSurface() != null) rental.setSurface(updateRentalDTO.getSurface());
    if (updateRentalDTO.getPrice() != null) rental.setPrice(updateRentalDTO.getPrice());

    rentalRepository.save(rental);
  }

  private String saveImage(MultipartFile image) throws IOException {
    Path lien = Paths.get(path);
    String fileName = UUID.randomUUID().toString() + image.getOriginalFilename();
    Files.createDirectories(lien);
    Path cible = lien.resolve(fileName);
    Files.copy(image.getInputStream(), cible, StandardCopyOption.REPLACE_EXISTING);
    return "api/images/" + fileName;
  }
}
