package com.openclassrooms.chatop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

  // Gérer les erreurs 400 - Bad Request
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(
      HttpStatus.BAD_REQUEST.value(),
      ex.getMessage(),
      LocalDateTime.now()
    ));
  }

  // Gérer les erreurs 401 - Unauthorized
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ApiError> handleUnauthorized(UnauthorizedException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiError(
      HttpStatus.UNAUTHORIZED.value(),
      ex.getMessage(),
      LocalDateTime.now()
    ));
  }

  // Gérer les erreurs inattendues (500 - Internal Server Error)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleInternalServerError(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(
      HttpStatus.INTERNAL_SERVER_ERROR.value(),
      "Une erreur interne est survenue.",
      LocalDateTime.now()
    ));
  }
}
