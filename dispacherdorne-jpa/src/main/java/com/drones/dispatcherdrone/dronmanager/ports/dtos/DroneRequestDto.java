package com.drones.dispatcherdrone.dronmanager.ports.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DroneRequestDto {
    @Size(max = 100)
    @NotBlank
    private String serial;
    @Size(min=2, max = 15)
    @NotBlank
    private String model;

    @Positive(message = "Please enter a valid number")
    private Integer weight;
}
