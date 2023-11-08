package org.polytech.covid.controller;

import java.util.List;

import org.polytech.covid.domain.Patient;
import org.polytech.covid.security.services.PatientService;
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
public class PatientController {
    
    @Autowired
    private PatientService PatientRepository;

    @GetMapping("/public/patients")
    public List<Patient> getAllPatient() {
        return PatientRepository.findAll();
    }

    @PostMapping("/public/patient/")
    public ResponseEntity<?> create(@RequestBody Patient patient) {
        System.out.println(patient.getId());
        PatientRepository.addPatient(patient);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/public/patient/{id}")
    public Patient get(@PathVariable Integer id) {
        return PatientRepository.findById(id);
    }

    @PutMapping("/public/patient/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Patient patient) {
        Patient patientToUpdate = PatientRepository.findById(id);
        if (patientToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        patientToUpdate.setLastname(patient.getLastname());
        patientToUpdate.setFirstname(patient.getFirstname());
        patientToUpdate.setEmail(patient.getEmail());
        patientToUpdate.setPhone(patient.getPhone());
        patientToUpdate.setBirthdate(patient.getBirthdate());
        patientToUpdate.setNbVaccin(patient.getNbVaccin());
        PatientRepository.addPatient(patientToUpdate);
        
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/public/patient/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Patient patientToDelete = PatientRepository.findById(id);
        if (patientToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        PatientRepository.deletePatient(id);
        return ResponseEntity.ok().build();
    }   
}
