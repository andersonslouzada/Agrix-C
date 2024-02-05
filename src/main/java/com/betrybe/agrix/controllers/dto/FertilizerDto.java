package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * The type Fertilizer dto.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition, null);
  }

  /**
   * To dto fertilizer dto.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer dto
   */
  public static FertilizerDto toDto(Fertilizer fertilizer) {
    return new FertilizerDto(fertilizer.getId(), fertilizer.getName(), fertilizer.getBrand(),
        fertilizer.getComposition());
  }
}
