// PanierItem.java
package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PanierItem")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PanierItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Panier panier;

    @ManyToOne
    private Evenement evenement;

    private int quantity;
    private double price;
}