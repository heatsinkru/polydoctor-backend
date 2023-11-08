package org.polytech.covid.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "email"),
           @UniqueConstraint(columnNames = "phone")
       })
public class Patient {
    
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private Date birthdate;

    @NotBlank
    private Number nbVaccin;

    @OneToMany(mappedBy = "patient", cascade = {})
    private List<Rdv> rdvs;

    public Patient() {
        this.rdvs = new ArrayList<>();
    }

    public Patient(String firstname, String lastname, String email, String phone, Date birthdate, Number nbVaccin) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPhone(phone);
        setBirthdate(birthdate);
        setNbVaccin(nbVaccin);
        this.rdvs = new ArrayList<>();
    }
    
    // GET
    public Long getId() {
        return this.id;
    }
    public String getFirstname() {
        return this.firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhone() {
        return this.phone;
    }
    public Date getBirthdate() {
        return this.birthdate;
    }
    public Number getNbVaccin() {
        return this.nbVaccin;
    }
    public List<Rdv> getRdvs() {
        return this.rdvs;
    }
    public Rdv getRdv(Integer id) {
        return this.rdvs.get(id);
    }

    // SET
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname= lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone= phone;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public void setNbVaccin(Number nbVaccin) {
        this.nbVaccin = nbVaccin;
    }
    public void addRdv(Rdv rdv) {
        this.rdvs.add(rdv);
    }
    public void removeRdv(Rdv rdv) {
        this.rdvs.remove(rdv);
    }
}
