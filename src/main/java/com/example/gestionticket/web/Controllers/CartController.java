package com.example.gestionticket.web.Controllers;

import com.example.gestionticket.Entities.Cart;
import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String getCart(Model model) {
        return "Cart/cart";
    }

    @GetMapping("/cart/{userId}")
        public String getCartByUser(@PathVariable Long userId, Model model) {
        Cart cart = cartService.getCartByUser(userId);
        if (cart == null) {
            return "redirect:/cart?userId=" + userId;
        }
    model.addAttribute("cart", cart);
    return "/cart";
}

    @PostMapping("/add")
    public String addEvenementToCart(@RequestParam Long cartId, @RequestParam Evenement evenement, @RequestParam int quantity) {
        cartService.addEvenementToCart(cartId, evenement, quantity);
        return "redirect:/cart/" + cartId;
    }

    @PostMapping("/remove")
    public String removeEvenementFromCart(@RequestParam Long cartId, @RequestParam Long evenementId) {
        cartService.removeEvenementFromCart(cartId, evenementId);
        return "redirect:/cart/" + cartId;
    }
}
