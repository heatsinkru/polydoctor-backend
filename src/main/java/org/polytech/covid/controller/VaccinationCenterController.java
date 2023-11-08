package org.polytech.covid.controller;

import java.util.List;

import org.polytech.covid.domain.VaccinationCenter;
import org.polytech.covid.security.services.VaccinationCenterService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class VaccinationCenterController {
    
    @Autowired
    private VaccinationCenterService CenterRepository;

    @GetMapping("/public/centers")
    public List<VaccinationCenter> getAllCenter() {
        return CenterRepository.findAll();
    }

    @PostMapping("/public/center/")
    public ResponseEntity<?> create(@RequestBody VaccinationCenter center) {
        CenterRepository.addCenter(center);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/public/center/{id}")
    public VaccinationCenter get(@PathVariable Integer id) {
        return CenterRepository.findById(id);
    }

    @PutMapping("/public/center/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VaccinationCenter center) {
        VaccinationCenter centerToUpdate = CenterRepository.findById(id);
        if (centerToUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        centerToUpdate.setName(center.getName());
        centerToUpdate.setAddress(center.getAddress());
        centerToUpdate.setCity(center.getCity());
        centerToUpdate.setPostalCode(center.getPostalCode());
        CenterRepository.addCenter(centerToUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/public/center/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        VaccinationCenter centerToDelete = CenterRepository.findById(id);
        if (centerToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        CenterRepository.deleteCenter(id);
        return ResponseEntity.ok().build();
    }   
}
