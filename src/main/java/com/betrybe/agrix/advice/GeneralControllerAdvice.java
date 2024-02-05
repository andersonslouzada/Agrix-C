package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.exception.PersonExistsException;
import com.betrybe.agrix.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type General controller advice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Handle farm not found exception response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(FarmNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  @ExceptionHandler
  public ResponseEntity<String> handleGenericException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno!");
  }

  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFoundException(FertilizerNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
  }

  @ExceptionHandler(PersonExistsException.class)
  public ResponseEntity<String> handlePersonAlreadyExistsException(PersonExistsException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa já existente no banco!");
  }

  @ExceptionHandler(PersonNotFoundException.class)
  public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Pessoa não encontrada!");
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Credenciais inválidas!");
  }

}
