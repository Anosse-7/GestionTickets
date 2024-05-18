// Panier.java
package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Panier")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "panier")
    private List<PanierItem> items;

    private double totalPrix;

    public Panier(Long panierId) {
    }
}