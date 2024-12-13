package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.service.MessageService;
import com.openclassrooms.chatop.service.UserService;
import com.openclassrooms.chatop.service.dto.CreateMessageDTO;
import com.openclassrooms.chatop.service.dto.MessageResponseDTO;
import com.openclassrooms.chatop.service.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private UserService userService;


  @Operation(summary = "Crée un nouveau message", description = "Crée un message en assignant automatiquement l'utilisateur connecté et le rental concerné")
  @ApiResponse(responseCode = "200", description = "Message envoyer avec succès")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @ApiResponse(responseCode = "400", description = "Erreur à la création")
  @PostMapping
  public ResponseEntity<MessageResponseDTO> createMessage(@RequestBody CreateMessageDTO createMessageDTO) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    UserDTO user = userService.findByMail(email);
    Message message = messageService.createMessage(createMessageDTO.getRental_id(), user.getId(), createMessageDTO.getMessage());

    MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
    messageResponseDTO.setMessage(message.getMessage());

    return ResponseEntity.ok(messageResponseDTO);
  }
}
