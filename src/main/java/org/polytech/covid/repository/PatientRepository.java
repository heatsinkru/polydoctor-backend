package org.polytech.covid.repository;

import org.polytech.covid.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    
    List<Patient> findAllByEmail(String email);
    List<Patient> findAllByPhone(String phone);
    List<Patient> findAllByFirstname(String firstname);
    List<Patient> findAllByLastname(String lastname);

}
