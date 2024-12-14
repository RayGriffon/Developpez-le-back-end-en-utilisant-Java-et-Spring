package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.exceptions.BadRequestException;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import com.openclassrooms.chatop.service.dto.RentalDTO;
import com.openclassrooms.chatop.service.dto.RentalFormDTO;
import com.openclassrooms.chatop.service.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
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

  @Autowired
  private UserService userService;

  public Rental findById(Integer id) {
    return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental non trouvé"));
  }

  public Rental findEntityById(int rentalId) {
    return rentalRepository.findById(rentalId)
      .orElseThrow(() -> new IllegalArgumentException("Rental non trouvé"));
  }


  public List<RentalDTO> getAllRentals() {
    try {
      return rentalMapper.toListRentalDTO(rentalRepository.findAll());
    } catch (Exception ex) {
      throw new UnauthorizedException("Vous n'êtes pas autorisé à voir cette liste.");
    }
  }

  public RentalDTO getRentalById(Integer id) {
    Rental rental = rentalRepository.findById(id)
            .orElseThrow(() -> new UnauthorizedException("Rental non trouvé ou inaccessible."));
    return rentalMapper.toRentalDTO(rental);
  }

  public void createRental(RentalFormDTO rentalDTO, MultipartFile multipartFile){
    try {
      String url = saveImage(multipartFile);
      rentalRepository.save(rentalMapper.toRental(rentalDTO, url));
    } catch (IOException ex) {
      throw new UnauthorizedException("Création de Rental non autorisée");
    }
  }

  public Rental updateRental(Integer id, Integer userId, String name, String description, BigDecimal surface, BigDecimal price) {

    Rental rental = rentalRepository.findById(id)
      .orElseThrow(() -> new BadRequestException("Rental non trouvé"));

    if (!rental.getOwner().getId().equals(userId)) {
      throw new UnauthorizedException("Vous n'êtes pas autorisé à modifier ce rental");
    }

    rental.setOwner(userService.findById(userService.findById(userId).getId()));
    rental.setName(name);
    rental.setDescription(description);
    rental.setSurface(surface);
    rental.setPrice(price);

    return rentalRepository.save(rental);
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
