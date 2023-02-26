package com.projet.pfe.entities;

import com.projet.pfe.enumerations.City;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class VaccinatedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String phoneNumber;
    private String address;
    private String gender;
    private String illness;
    private String medicine;
    private boolean pregnant;
    private LocalDate birthDate;
    private City city;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Facility facility;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUserDetails appUserDetails;

    public VaccinatedUser(String phoneNumber,
                          String address,
                          String gender,
                          String illness,
                          String medicine,
                          boolean pregnant,
                          LocalDate birthDate,
                          City city,
                          Facility facility,
                          AppUserDetails appUserDetails) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.illness = illness;
        this.medicine = medicine;
        this.pregnant = pregnant;
        this.birthDate = birthDate;
        this.city = city;
        this.facility = facility;
        this.appUserDetails = appUserDetails;
    }
}
