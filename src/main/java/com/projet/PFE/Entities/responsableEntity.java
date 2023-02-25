package com.projet.PFE.Entities;

import jakarta.persistence.*;

@Entity
@Table
public class responsableEntity extends userEntity{
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
    private boolean active;
    @OneToOne
    @JoinColumn(name = "centreId", referencedColumnName = "Id")
    private centreEntity centreId;

    public responsableEntity() {
    }

    public responsableEntity(String role, String cin, String password, String nom, String prenom, String email, centreEntity centreId, boolean active) {
        super(role,cin,password);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.active= active;
        this.centreId = centreId;
    }



    public Long getId() {return Id;}

    public void setId(Long respoId) {this.Id = Id;}

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

    public centreEntity getCentreId() {
        return centreId;
    }

    public void setCentreId(centreEntity centreId) {
        centreId = centreId;
    }

    public boolean
    isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "responsableEntity{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", centreId=" + centreId +
                '}';
    }


}
