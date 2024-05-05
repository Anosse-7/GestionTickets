package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Event")
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, name = "nom")
    private String nom;

    @Column(length = 45, nullable = false, name = "prenom")
    private String prenom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEvenement;

    @Column
    private String typeEvenement;

    // Constructeurs, getters et setters...

    public Evenement() {
        // Constructeur par défaut nécessaire pour JPA
    }

    public Evenement(String nom, String prenom, Date dateEvenement, String typeEvenement) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateEvenement = dateEvenement;
        this.typeEvenement = typeEvenement;
    }

    // Getters et Setters pour id, nom, prenom, dateEvenement, typeEvenement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }
}
