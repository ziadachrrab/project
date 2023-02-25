package com.projet.PFE.Entities;

import com.projet.PFE.Enumerations.villeEnumeration;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class vaccinatedEntity {
    @Id
    @SequenceGenerator(
            name = "vaccinated_sequence",
            sequenceName = "vaccinated_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vaccinated_sequence"
    )

    private int Id;
    private String nom;
    private String prenom;
    private String cin;
    private String n_tele;
    private String addresse;
    private String sexe;
    private String maladie;
    private String medicament;
    private boolean enceinte;
    private villeEnumeration ville;
    @ManyToOne
    @JoinColumn(name = "centreId", referencedColumnName = "Id")
    private centreEntity centreId;
    private Date date_naissance;

    public vaccinatedEntity() {
    }

    public vaccinatedEntity(String nom, String prenom, String cin, String n_tele, String addresse, String sexe,
                            String maladie, String medicament, boolean enceinte, villeEnumeration ville, centreEntity centreId, Date date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.n_tele = n_tele;
        this.addresse = addresse;
        this.sexe = sexe;
        this.maladie = maladie;
        this.medicament = medicament;
        this.enceinte = enceinte;
        this.ville = ville;
        this.centreId = centreId;
        this.date_naissance = date_naissance;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getN_tele() {
        return n_tele;
    }

    public void setN_tele(String n_tele) {
        this.n_tele = n_tele;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public boolean isEnceinte() {
        return enceinte;
    }

    public void setEnceinte(boolean enceinte) {
        this.enceinte = enceinte;
    }

    public villeEnumeration getVille() {
        return ville;
    }

    public void setVille(villeEnumeration ville) {
        this.ville = ville;
    }

    public centreEntity getCentreId() {
        return centreId;
    }

    public void setCentreId(centreEntity centreId) {
        this.centreId = centreId;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "vaccinatedEntity{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", CIN='" + cin + '\'' +
                ", n_tele='" + n_tele + '\'' +
                ", addresse='" + addresse + '\'' +
                ", sexe='" + sexe + '\'' +
                ", maladie='" + maladie + '\'' +
                ", medicament='" + medicament + '\'' +
                ", enceinte=" + enceinte +
                ", ville=" + ville +
                ", centreId=" + centreId +
                ", date_naissance=" + date_naissance +
                '}';
    }
}
