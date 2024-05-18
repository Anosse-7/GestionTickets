// PanierController.java
package com.example.gestionticket.web.Controllers;

import com.example.gestionticket.Entities.Panier;
import com.example.gestionticket.Entities.PanierItem;
import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/panier")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @GetMapping("/{userId}")
    public Panier getPanierByUser(@PathVariable Long userId) {
        return panierService.getPanierByUser(userId);
    }

    @PostMapping("/{panierId}/evenement/{evenementId}")
    public PanierItem addEvenementToPanier(@PathVariable Long panierId, @PathVariable Long evenementId, @RequestParam int quantity) {
        Evenement evenement = new Evenement();
        evenement.setId(evenementId);
        return panierService.addEvenementToPanier(panierId, evenement, quantity);
    }

    @DeleteMapping("/{panierId}/evenement/{evenementId}")
    public void removeEvenementFromPanier(@PathVariable Long panierId, @PathVariable Long evenementId) {
        panierService.removeEvenementFromPanier(panierId, evenementId);
    }

    @GetMapping("/{panierId}/total")
    public double calculateTotalPrice(@PathVariable Long panierId) {
        return panierService.calculateTotalPrice(panierId);
    }
}