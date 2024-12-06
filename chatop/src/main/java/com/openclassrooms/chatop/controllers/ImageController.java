package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.service.ImagesServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/images")
public class ImageController {

  @Autowired
  ImagesServices imagesServices;

  @Operation(summary = "Permet d'afficher les images")
  @ApiResponse(responseCode = "200", description = "Image trouvé")
  @ApiResponse(responseCode = "401", description = "Non autorisé")
  @GetMapping(value = "/{param}")
  public ResponseEntity<Resource> getMethodName(@PathVariable String param) throws IOException {
    Resource image = imagesServices.findImageOnDisk(param);
    String contentType = Files.probeContentType(image.getFile().toPath());
    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))
      .body(image);
  }
}
