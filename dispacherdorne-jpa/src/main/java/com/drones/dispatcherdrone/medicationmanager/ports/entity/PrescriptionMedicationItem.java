package com.drones.dispatcherdrone.medicationmanager.ports.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "prescription_medication_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter

@Builder(toBuilder = true)
public class PrescriptionMedicationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(length = 120)
    @Min(2)
    private String medicationName;

    @Column(length = 3)
    @Min(1)
    @Max(2)
    private Integer weight;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "load_id")
    private PrescriptionLoad prescriptionLoad;

}
