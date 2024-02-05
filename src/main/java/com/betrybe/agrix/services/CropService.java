package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository       the crop repository
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }


  /**
   * Gets crop by id.
   *
   * @param id the crop id
   * @return the crop by id
   */
  public Crop getCropById(Long id) {
    Optional<Crop> cropOptional = cropRepository.findById(id);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException();
    }

    return cropOptional.get();
  }

  /**
   * Find by harvest date interval list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  public List<Crop> findByHarvestDateInterval(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Insert fertilizer to crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  public void insertFertilizerToCrop(Long cropId, Long fertilizerId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(fertilizerId);
    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = fertilizerOptional.get();
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   */
  public List<Fertilizer> getFertilizersByCropId(Long cropId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
    return crop.getFertilizers();
  }
}
