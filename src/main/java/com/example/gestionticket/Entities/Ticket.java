package com.example.gestionticket.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ticket")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String titre;
    @Column
    private String description;
    @Column
    private String etat;
    @Column
    private String quantite;
    @Column
    private String photo;
    @Column
    private Double prix;
    @Column(columnDefinition = "float default 0")
    private double reduction;
    @Transient
    private double reducedPrice;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "evenement_id", nullable = false)
    private Evenement evenement;
}
