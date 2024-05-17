package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Cart;
import com.example.gestionticket.Entities.CartItem;
import com.example.gestionticket.Entities.CartItemDetails;
import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Repository.CartRepository;
import com.example.gestionticket.Repository.CartItemRepository;
import com.example.gestionticket.Repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final EvenementRepository evenementRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, EvenementRepository evenementRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.evenementRepository = evenementRepository;
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
    public CartItem addEvenementToCart(Long cartId, Long evenementId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Evenement evenement = evenementRepository.findById(evenementId).orElseThrow(() -> new RuntimeException("Evenement not found"));
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setEvenement(evenement);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(evenement.getPrice() * quantity);
        return cartItemRepository.save(cartItem);
}
    @Override
    public CartItemDetails getCartItemDetails(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));
        String titre = cartItem.getEvenement().getTitre();
        String description = cartItem.getEvenement().getDescription();
        return new CartItemDetails(titre, description, cartItem.getQuantity(), cartItem.getPrice());
    }
    @Override
public void removeEvenementFromCart(Long cartId, Long evenementId) {
    Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    Optional<CartItem> cartItem = cart.getItems().stream().filter(item -> item.getEvent().getId().equals(evenementId)).findFirst();
    cartItem.ifPresent(cartItemRepository::delete);
}

    @Override
    public double calculateTotalPrice(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getItems().stream().mapToDouble(CartItem::getPrice).sum();
    }
}