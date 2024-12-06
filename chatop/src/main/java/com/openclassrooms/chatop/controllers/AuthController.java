package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.AuthService;
import com.openclassrooms.chatop.service.dto.LoginDTO;
import com.openclassrooms.chatop.service.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Operation(summary = "Créer un nouvel utilisateur", description = "Crée un nouvel utilisateur en renseignant le mail (qui doit être unique), le nom et le mot de passe. Renvoi un JWT")
  @ApiResponse(responseCode = "200", description = "Utilisateur enregistré")
  @ApiResponse(responseCode = "400", description = "Erreur durant la création de l'utilisateur")
  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO userDTO) {
    String token = authService.register(userDTO);
    return ResponseEntity.ok(Map.of("token", token));
  }

  @Operation(summary = "Connecte un utilisateur", description = "Permet de se connecter en utilisant un email et un mot de passe. Renvoi un JWT")
  @ApiResponse(responseCode = "200", description = "Connection réussi")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginRequest) {
    String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    return ResponseEntity.ok(Map.of("token", token));
  }

  @Operation(summary = "Récupére l'utilisateur actuel", description = "Affiche les données de l'utilisateur actuel")
  @ApiResponse(responseCode = "200", description = "Utilisateur trouvé")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @GetMapping("/me")
  public ResponseEntity<UserDTO> me(Authentication authentication) {
    return ResponseEntity.ok(authService.getMe(authentication));
  }
}
