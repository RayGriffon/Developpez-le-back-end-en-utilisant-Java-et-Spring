package com.openclassrooms.chatop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImagesServices {

  @Value("${picture.upload}")
  private String path;

  public Resource findImageOnDisk(String uuid) throws MalformedURLException {
    Path filePath = Paths.get(path).resolve(uuid).normalize();
    Resource resource = new UrlResource(filePath.toUri());
    if (resource.exists() && resource.isReadable()) {
      return resource;
    } else {
      throw new EntityNotFoundException("Picture not found");
    }
  }
}
