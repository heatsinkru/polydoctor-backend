package org.polytech.covid.security.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.polytech.covid.domain.Patient;
import org.polytech.covid.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PatientService {

    @Autowired
    private PatientRepository PatientRepository;

    public Patient findById(Integer id) {
        Optional<Patient> patient = PatientRepository.findById(id);
        if (patient == null) {
            return null;
        }
        return patient.get();
    }

    public List<Patient> findAll() {
        return PatientRepository.findAll();
    }

    public List<Patient> findAllByLastname(Patient patient) {
        return PatientRepository.findAllByLastname(patient.getLastname());
    }

    public void addPatient(Patient patient) {
        PatientRepository.save(patient);
    }

    public void deletePatient(Integer id) {
        PatientRepository.deleteById(id);
    }
}
