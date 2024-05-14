// CartService.java
package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Cart;
import com.example.gestionticket.Entities.CartItem;
import com.example.gestionticket.Entities.Evenement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    Cart getCartByUser(Long userId);
    CartItem addEvenementToCart(Long cartId, Evenement evenement, int quantity);
    void removeEvenementFromCart(Long cartId, Long evenementId);
    double calculateTotalPrice(Long cartId);
}