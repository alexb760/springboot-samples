package com.drones.dispatcherdrone.medicationmanager.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "medication")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(length = 120)
    private String name;

    @Column(length = 3)
    private Double weight;

    @Column(length = 100)
    @Size(min = 1, max = 100)
    private String code;

    private String image;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "load_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PrescriptionLoad prescriptionLoad;

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
      return false;
        }
        return uuid != null && uuid.equals( ((Medication)obj).uuid);
    }

    @Override
    public int hashCode() {
        return 2023;
    }
}
