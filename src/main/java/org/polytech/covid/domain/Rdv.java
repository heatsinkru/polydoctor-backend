package org.polytech.covid.domain;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rdv")
public class Rdv {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Date date;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Patient patient;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private User user;
    
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private VaccinationCenter vaccinationCenter;

    public Rdv( Date date, Patient patient, User user, VaccinationCenter vaccinationCenter) {
        setDate(date);
        setPatient(patient);
        setUser(user);
        setVaccinationCenter(vaccinationCenter);
    }

    // GET
    public long getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }
    public Patient getPatient() {
        return patient;
    }
    public User getUser() {
        return this.user;
    }
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }


    // SET
    public void setId(Long id) {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

}
