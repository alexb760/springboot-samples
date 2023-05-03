package com.drones.dispatcherdrone.dronmanager.ports.repository;

import com.drones.dispatcherdrone.dronmanager.ports.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {}
