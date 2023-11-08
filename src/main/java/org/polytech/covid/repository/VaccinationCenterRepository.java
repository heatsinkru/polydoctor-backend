package org.polytech.covid.repository;

import org.polytech.covid.domain.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer> {

    List<VaccinationCenter> findAllByCity(String city);
    List<VaccinationCenter> findAllByName(String name);
    List<VaccinationCenter> findAllById(Integer id);

}