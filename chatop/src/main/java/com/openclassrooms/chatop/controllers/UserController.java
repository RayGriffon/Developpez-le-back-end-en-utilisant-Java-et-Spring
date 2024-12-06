package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.UserService;
import com.openclassrooms.chatop.service.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Operation(summary = "Trouver un utilisateur", description = "Retourne l'utilisateur associé à l'id")
  @ApiResponse(responseCode = "200", description = "Utilisateur trouvé")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }
}
