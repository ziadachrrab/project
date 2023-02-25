package com.projet.PFE.Entities;

import com.projet.PFE.Enumerations.villeEnumeration;
import jakarta.persistence.*;


@Entity
@Table
public class centreEntity {
    @Id
    @SequenceGenerator(
            name = "centre_sequence",
            sequenceName = "centre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "centre_sequence"
    )

    private Long Id;
    @Column()
    private String nom;
    @Column()
    private String addresse;
    @Column()
    private long capacitee;
    @Column()
    @Enumerated(EnumType.STRING)
    private villeEnumeration ville;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsableId")
    private responsableEntity responsable_Id;

    public centreEntity() {
    }

    public centreEntity(String nom, String addresse, long capacitee, responsableEntity responsable_Id, villeEnumeration ville) {
        this.nom = nom;
        this.addresse = addresse;
        this.capacitee = capacitee;
        this.responsable_Id = responsable_Id;
        this.ville = ville;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public long getCapacitee() {
        return capacitee;
    }

    public void setCapacitee(long capacitee) {
        this.capacitee = capacitee;
    }

    public responsableEntity getResponsableId() {
        return responsable_Id;
    }

    public void setResponsableId(responsableEntity responsable_Id) {
        this.responsable_Id = responsable_Id;
    }

    public villeEnumeration getVille() {
        return ville;
    }

    public void setVille(villeEnumeration ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "centreEntity{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", addresse='" + addresse + '\'' +
                ", capacitee=" + capacitee +
                ", ville=" + ville +
                ", responsable_cin=" + responsable_Id +
                '}';
    }

}
