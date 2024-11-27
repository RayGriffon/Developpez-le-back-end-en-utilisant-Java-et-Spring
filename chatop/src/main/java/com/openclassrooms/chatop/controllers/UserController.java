package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.UserService;
import com.openclassrooms.chatop.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }
}
