package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.MessageService;
import com.openclassrooms.chatop.service.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @PostMapping
  public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageDTO messageDTO) {
    messageService.saveMessage(messageDTO);
    return ResponseEntity.ok(Map.of("message", "Message saved"));
  }
}
