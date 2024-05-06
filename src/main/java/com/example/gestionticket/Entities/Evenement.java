package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String titre;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEvenement;

    @Column
    private String typeEvenement;


    public Evenement(String titre, Date dateEvenement, String typeEvenement) {
        this.titre = titre;
        this.dateEvenement = dateEvenement;
        this.typeEvenement = typeEvenement;
    }
}
