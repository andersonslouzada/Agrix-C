package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm response entity.
   *
   * @param farmDto the farm dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.createFarm(farmDto.toFarm());

    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.toDto(newFarm));
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    List<FarmDto> allFarmsDto = allFarms.stream()
        .map(FarmDto::toDto).toList();

    return ResponseEntity.ok(allFarmsDto);
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);

    return ResponseEntity.ok(farm);
  }

  /**
   * Insert crop response entity.
   *
   * @param id      the id
   * @param cropDto the crop dto
   * @return the response entity
   */
  @PostMapping("/{id}/crops")
  public ResponseEntity<CropDto> insertCrop(@PathVariable Long id,
      @RequestBody CropDto cropDto) {
    Crop newCrop = farmService.insertCrop(id, cropDto.toCrop());
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.toDto(newCrop));
  }

  /**
   * Gets crops by farm id.
   *
   * @param id the id
   * @return the crops by farm id
   */
  @GetMapping("/{id}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(@PathVariable Long id) {
    List<Crop> crops = farmService.getCropsByFarmId(id);
    List<CropDto> cropsDto = crops.stream()
        .map(CropDto::toDto)
        .toList();
    return ResponseEntity.ok(cropsDto);
  }
}
