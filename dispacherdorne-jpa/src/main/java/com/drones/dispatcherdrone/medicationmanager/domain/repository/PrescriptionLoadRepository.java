package com.drones.dispatcherdrone.medicationmanager.domain.repository;

import com.drones.dispatcherdrone.medicationmanager.domain.entity.PrescriptionLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrescriptionLoadRepository extends JpaRepository<PrescriptionLoad, Long> {

    @Query("SELECT p FROM PrescriptionLoad p JOIN FETCH p.items WHERE p.orderCode = ?1")
    PrescriptionLoad fetchByOrderCode(String orderCode);
}
