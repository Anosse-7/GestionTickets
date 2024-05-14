package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Cart;
import com.example.gestionticket.Entities.CartItem;
import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public CartItem addEvenementToCart(Long cartId, Evenement evenement, int quantity) {
        return null;
    }

    @Override
    public void removeEvenementFromCart(Long cartId, Long evenementId) {

    }

    @Override
    public double calculateTotalPrice(Long cartId) {
        return 0;
    }
}