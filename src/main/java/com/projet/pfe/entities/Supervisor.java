package com.projet.pfe.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table
@Entity
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean active;
    @OneToOne(mappedBy = "supervisor")
    private Facility facility;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUserDetails appUserDetails;

    public Supervisor(boolean active, Facility facility, AppUserDetails appUserDetails) {
        this.active = active;
        this.facility = facility;
        this.appUserDetails = appUserDetails;
    }
}
