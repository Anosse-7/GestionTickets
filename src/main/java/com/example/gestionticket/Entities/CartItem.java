// CartItem.java
package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CartItem")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Evenement evenement;

    private int quantity;
    private double price;
}