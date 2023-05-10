package com.drones.dispatcherdrone.medicationmanager.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "prescription_load")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrescriptionLoad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty()
  private Long loadId;

  @Column(length = 45, nullable = false)
  private String orderCode;

  @Column(length = 3, nullable = false)
  @Min(1)
  @Max(500)
  private Double totalWeight;

  @Column(nullable = false, name = "created_at")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;

  @Column(length = 15)
  private String status;

  @JsonManagedReference
  @OneToMany(
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      mappedBy = "prescriptionLoad",
      fetch = FetchType.LAZY)
  private Set<Medication> items;

  public static PrescriptionLoad withCodeOrder(String codeOrder) {
    return new PrescriptionLoad(null, codeOrder, 0D, LocalDateTime.now(), "READY", new HashSet<>());
  }

  // Keeping boots side of relation in Sync by some handlers methods.
  public PrescriptionLoad addMedicationItem(Medication item) {
    this.items.add(item);
    this.totalWeight = this.totalWeight + item.getWeight();
    item.setPrescriptionLoad(this);
    return this;
  }

  public void removeMedicationItem(Medication item) {
    item.setPrescriptionLoad(null);
    this.items.remove(item);
    this.totalWeight = this.totalWeight - item.getWeight();
  }

  public void removeAllItem() {
    Iterator<Medication> iterator = this.items.iterator();
    while (iterator.hasNext()) {
      var item = iterator.next();
      item.setPrescriptionLoad(null);
      iterator.remove();
    }
    this.totalWeight = 0D;
  }

  public void checkLoadWeightCapacity() {
    if (this.totalWeight > 500) {
      throw new IllegalArgumentException("Load capacity should be less or equal to 500 gr");
    }
  }
  // end of handlers.

  /**
   * As a good practice we override the toString method to avoid the @OneToMany relationship be
   * triggered and may throw an exception or execute a second SQL to the database.
   *
   * @return String
   */
  @Override
  public String toString() {
    return String.format(
        "PrescriptionLoad:{loadId: %d, orderCode: %s, totalWeight: %s, createdAt: %s}",
        loadId, orderCode, totalWeight, createdAt.toString());
  }
}
