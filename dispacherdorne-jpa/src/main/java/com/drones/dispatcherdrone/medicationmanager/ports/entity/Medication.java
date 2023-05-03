package com.drones.dispatcherdrone.medicationmanager.ports.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "medication")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(length = 120)
    @Min(2)
    private String name;

    @Column(length = 3)
    private Integer weight;

    @Column(length = 100)
    @Min(5)
    private String code;

    private String image;

}
