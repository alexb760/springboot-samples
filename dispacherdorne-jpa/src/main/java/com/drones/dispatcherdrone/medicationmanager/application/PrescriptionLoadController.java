package com.drones.dispatcherdrone.medicationmanager.application;

import com.drones.dispatcherdrone.medicationmanager.domain.entity.PrescriptionLoad;
import com.drones.dispatcherdrone.medicationmanager.domainservice.PrescriptionLoadDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class PrescriptionLoadController {
    private final PrescriptionLoadDomainService loadDomainService;

    @PostMapping
    public PrescriptionLoad createPrescriptionLoad(){
    return loadDomainService.createDeliveryLoad();
    }
    @PostMapping("/editOrden")
    public PrescriptionLoad edirPrescriptionLoad(){
    return loadDomainService.addNewItemToOrder();
    }

    @GetMapping
    @ResponseBody
    public List<PrescriptionLoad> getPrescriptionLoad(){
    return loadDomainService.getALlPrescriptionLoad();
  }
}
