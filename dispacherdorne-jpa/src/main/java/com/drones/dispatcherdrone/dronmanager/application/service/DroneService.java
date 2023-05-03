package com.drones.dispatcherdrone.dronmanager.application.service;

import com.drones.dispatcherdrone.dronmanager.ports.dtos.DroneRequestDto;
import com.drones.dispatcherdrone.dronmanager.ports.entity.Drone;
import com.drones.dispatcherdrone.dronmanager.ports.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;

    @Transactional
    public Drone registerNewDrone(DroneRequestDto requestDto){
        Drone newDrone = Drone.builder()
                .serial(requestDto.getSerial())
                .model(requestDto.getModel())
                .weight(requestDto.getWeight())
                .state(DroneState.IDLE.name())
                .batteryCapacity(100)
                .build();
        return droneRepository.save(newDrone);
    }

    //For the time being we use traditional pageable methods.
    // future improvements will implements an aggregate function to count the total number of records
    // so that we  avoid the double trip to database
    // select *, count(*) over() as total_record from drone;
    // or annotation @countQuery
    public Page<Drone> listAllDrones(Pageable pageable){
    return droneRepository.findAll(pageable);
  }
}
