package com.drones.dispatcherdrone.medicationmanager.ports.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Entity
@Table(name = "prescription_load")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PrescriptionLoad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long loadId;

  @Column(length = 45, nullable = false)
  @Min(5)
  @Max(45)
  private String orderCode;

  @Column(length = 3, nullable = false)
  @Min(1)
  @Max(500)
  private Integer totalWeight;

  @Column(nullable = false, name = "created_at")
  private LocalDateTime createdAt;

  @Builder.Default
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "prescriptionLoad")
  private Collection<PrescriptionMedicationItem> items = new ArrayList<>();

  // Keeping boots side of relation in Sync by some handlers methods.
  public void addmedicationItem(PrescriptionMedicationItem item) {
    this.items.add(item);
    item.setPrescriptionLoad(this);
  }

  public void removeMedicationItem(PrescriptionMedicationItem item) {
    item.setPrescriptionLoad(null);
    this.items.remove(item);
  }

  public void removeAllItem() {
    Iterator<PrescriptionMedicationItem> iterator = this.items.iterator();
    while (iterator.hasNext()) {
      PrescriptionMedicationItem item = iterator.next();
      item.setPrescriptionLoad(null);
      iterator.remove();
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
        "PrescriptionLoad:{loadId: %d, orderCode: %s, totalWeight: %d, createdAt: %s}",
        loadId, orderCode, totalWeight, createdAt.toString());
  }
}
