package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * The type Person response dto.
 */
public record PersonResponseDto(Long id, String username, Role role) {

  /**
   * To dto person response dto.
   *
   * @param person the person
   * @return the person response dto
   */
  public static PersonResponseDto toDto(Person person) {
    return new PersonResponseDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}
