package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    List<CropDto> allCropsDto = allCrops.stream()
        .map(CropDto::toDto)
        .toList();

    return ResponseEntity.ok(allCropsDto);
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);

    return ResponseEntity.ok(CropDto.toDto(crop));
  }

  /**
   * Gets crops by harvest day.
   *
   * @param start the start date
   * @param end   the end date
   * @return the crops by harvest day
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsByHarvestDay(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> crops = cropService.findByHarvestDateInterval(start, end);
    List<CropDto> cropDtos = crops.stream()
        .map(CropDto::toDto)
        .toList();
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Insert fertilizer to crop response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> insertFertilizerToCrop(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    cropService.insertFertilizerToCrop(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCropId(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getFertilizersByCropId(cropId);
    List<FertilizerDto> fertilizerDtos = fertilizers.stream()
        .map(FertilizerDto::toDto)
        .toList();

    return ResponseEntity.ok(fertilizerDtos);
  }

}
