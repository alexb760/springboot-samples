package com.drones.dispatcherdrone.dronmanager.ports.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drone")
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(length = 10, nullable = false)
    @Size(max = 100)
    private String serial;

    @Column(length = 15, nullable = false)
    @Size(min=2, max = 15)
    private String model;
    @Column(length = 3)
    private Integer weight;
    @Column(length = 3, nullable = false, name = "battery_capacity")
    @Min(1)
    @Max(100)
    private Integer batteryCapacity;

    @Column(length = 10, nullable = false)
    @Size(min = 3, max = 10)
    private String state;
}
