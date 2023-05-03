package com.drones.dispatcherdrone.dronmanager.ports.rest;

import com.drones.dispatcherdrone.dronmanager.application.service.DroneService;
import com.drones.dispatcherdrone.dronmanager.ports.dtos.DroneRequestDto;
import com.drones.dispatcherdrone.dronmanager.ports.entity.Drone;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drone")
public class DroneControllerRest {
  private final DroneService droneService;

  @PostMapping
  public Drone register(@RequestBody @Valid DroneRequestDto request) {
    return droneService.registerNewDrone(request);
  }

  @GetMapping
  public Page<Drone> fetchPageOfDrone(
      @SortDefault.SortDefaults({@SortDefault(sort = "serial", direction = Sort.Direction.ASC)})
          Pageable pageable) {
    return droneService.listAllDrones(pageable);
  }
}
