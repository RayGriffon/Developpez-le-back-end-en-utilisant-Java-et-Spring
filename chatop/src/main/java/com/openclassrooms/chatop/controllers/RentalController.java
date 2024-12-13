package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.RentalService;
import com.openclassrooms.chatop.service.UserService;
import com.openclassrooms.chatop.service.dto.RentalDTO;
import com.openclassrooms.chatop.service.dto.RentalFormDTO;
import com.openclassrooms.chatop.service.dto.UpdateRentalDTO;
import com.openclassrooms.chatop.service.dto.UserDTO;
import com.openclassrooms.chatop.service.mapper.RentalMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rentals")
@Tag(name = "Rentals", description = "Gestion des locations")
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @Autowired
  private UserService userService;

  @Autowired
  private RentalMapper rentalMapper;

  @Operation(summary = "Récupérer tout les rentals", description = "Retourne une liste de tout les rentals disponibles")
  @ApiResponse(responseCode = "200", description = "Liste des rentals récupérée avec succès")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @GetMapping
  public ResponseEntity<Map<String, List<RentalDTO>>> getAllRentals() {
    return ResponseEntity.ok(Map.of("rentals", rentalService.getAllRentals()));
  }

  @Operation(summary = "Trouver un rental", description = "Retourne le rental associé à l'id")
  @ApiResponse(responseCode = "200", description = "Rental trouvé")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @GetMapping("/{id}")
  public ResponseEntity<RentalDTO> getRentalById(@PathVariable Integer id) {
    return ResponseEntity.ok(rentalService.getRentalById(id));
  }

  @Operation(summary = "Crée un rental", description = "Crée un nouveau rental avec un nom, une surface, un prix, une image et une description")
  @ApiResponse(responseCode = "200", description = "Rental créé")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @PostMapping
  public ResponseEntity<Map<String, String>> createRental(
    @RequestParam("name") String name,
    @RequestParam("surface") BigDecimal surface,
    @RequestParam("price") BigDecimal price,
    @RequestParam("picture") MultipartFile picture,
    @RequestParam("description") String description,
    Principal principal) throws IOException {

    UserDTO user = userService.findByMail(principal.getName());

    RentalFormDTO rentalDTO = new RentalFormDTO(name, surface, price, description, user.getId());
    rentalService.createRental(rentalDTO, picture);
    return ResponseEntity.ok(Map.of("message", "Rental created!"));
  }

  @Operation(summary = "Actualise un rental")
  @ApiResponse(responseCode = "200", description = "Rental actualisé")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @PutMapping("/{id}")
  public ResponseEntity<RentalDTO> updateRental(@PathVariable Integer id, @RequestBody UpdateRentalDTO updateRentalDTO, Principal principal) {
    UserDTO userDTO = userService.findByMail(principal.getName());
    RentalDTO rental = rentalMapper.toRentalDTO(rentalService.updateRental(id, userDTO.getId(), updateRentalDTO.getName(), updateRentalDTO.getDescription(), updateRentalDTO.getSurface(), updateRentalDTO.getPrice()));

    return ResponseEntity.ok(rental);
  }
}
