// Cart.java
package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Cart")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Assuming you have a User entity
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    public Optional<List<CartItem>> getCartItems() {
        return Optional.ofNullable(items);
    }
}