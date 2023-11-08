package org.polytech.covid.repository;

import org.polytech.covid.domain.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface RdvRepository extends JpaRepository<Rdv, Integer> {

    Rdv findByDate(Date date);

}
