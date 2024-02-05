package com.betrybe.agrix.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Exception for when a person is not found.
 */
public class PersonNotFoundException extends UsernameNotFoundException {

  /**
   * Instantiates a new Person not found exception.
   */
  public PersonNotFoundException() {
    super("Pessoa não encontrada!");
  }

}
