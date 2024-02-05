package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Fertilizer service.
   *
   * @param fertilizerRepository the fertilizer repository
   */
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Create fertilizer fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   */
  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * Gets fertilizer by id.
   *
   * @param fetilizerId the id
   * @return the fertilizer by id
   */
  public Fertilizer getFertilizerById(Long fetilizerId) {
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fetilizerId);
    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }
    return optionalFertilizer.get();
  }
}
