package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Event")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false, name = "nom")
    private String nom;

    @Column(length = 45, nullable = false, name = "prenom")
    private String prenom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEvenement;

    @Column
    private String typeEvenement;

    // Constructeurs, getters et setters...

    public Evenement(String nom, String prenom, Date dateEvenement, String typeEvenement) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateEvenement = dateEvenement;
        this.typeEvenement = typeEvenement;
    }
}
