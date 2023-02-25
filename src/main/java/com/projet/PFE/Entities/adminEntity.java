package com.projet.PFE.Entities;

import jakarta.persistence.*;

@Entity
@Table
public class adminEntity extends userEntity{
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_sequence"
    )

    private Long Id;
    private String nom;
    private String prenom;
    private String email;

    public adminEntity() {
    }

    public adminEntity(String role, String CIN, String password, String nom, String prenom, String email) {
        super(role, CIN, password);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    @Override
    public Long getId() {return Id;}

    @Override
    public void setId(Long id) {Id = id;}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "adminEntity{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
