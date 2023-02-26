package com.projet.pfe.entities;

import com.projet.pfe.enumerations.City;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private long capacity;
    @Enumerated(EnumType.STRING)
    private City city;
    @OneToOne
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    private Supervisor supervisor;

    public Facility(String name, String address, long capacity, City city, Supervisor supervisor) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.city = city;
        this.supervisor = supervisor;
    }
}
