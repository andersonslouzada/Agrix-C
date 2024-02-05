package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropRepository the crop repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Create farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Gets farm by id.
   *
   * @param id the farm id
   * @return the farm by id
   */
  public Farm getFarmById(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);
    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return optionalFarm.get();
  }

  /**
   * Insert crop crop.
   *
   * @param id   the id
   * @param crop the crop
   * @return the crop
   */
  public Crop insertCrop(Long id, Crop crop) {
    Optional<Farm> farmOptional = farmRepository.findById(id);
    if (farmOptional.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Farm farm = farmOptional.get();
    crop.setFarm(farm);

    return cropRepository.save(crop);
  }

  /**
   * Gets crops by farm id.
   *
   * @param id the id
   * @return the crops by farm id
   */
  public List<Crop> getCropsByFarmId(Long id) {
    Optional<Farm> farmOptional = farmRepository.findById(id);
    if (farmOptional.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Farm farm = farmOptional.get();
    return farm.getCrops();
  }
}