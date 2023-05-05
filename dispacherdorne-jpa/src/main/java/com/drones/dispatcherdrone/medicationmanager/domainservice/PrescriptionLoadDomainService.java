package com.drones.dispatcherdrone.medicationmanager.domainservice;

import com.drones.dispatcherdrone.medicationmanager.domain.entity.Medication;
import com.drones.dispatcherdrone.medicationmanager.domain.entity.PrescriptionLoad;
import com.drones.dispatcherdrone.medicationmanager.domain.repository.PrescriptionLoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionLoadDomainService {
  private final PrescriptionLoadRepository loadRepository;

  public PrescriptionLoad createDeliveryLoad() {
    var item01 = Medication.builder().name("Aspiring").weight(100D).build();
    var item02 = Medication.builder().name("Med-2").weight(100D).build();
    var item03 = Medication.builder().name("med-3").weight(100D).build();
    var item04 = Medication.builder().name("med-4").weight(100D).build();

    PrescriptionLoad load = PrescriptionLoad.withCodeOrder("0000-1");

    load.addMedicationItem(item01)
        .addMedicationItem(item02)
        .addMedicationItem(item03)
        .addMedicationItem(item04)
        .checkLoadWeightCapacity();

    return loadRepository.save(load);
  }

  public PrescriptionLoad addNewItemToOrder() {
    var load = loadRepository.fetchByOrderCode("0000-1");
    var item03 = Medication.builder().name("med-3").weight(100D).build();
    load.addMedicationItem(item03).checkLoadWeightCapacity();
    return loadRepository.save(load);
  }

  public List<PrescriptionLoad> getALlPrescriptionLoad() {
    var allLoads = loadRepository.findAll();
    //        var collect = allLoads.stream().map(PrescriptionLoadDto::new).toList();
    return allLoads;
  }
}
