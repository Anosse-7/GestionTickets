// PanierService.java
package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Panier;
import com.example.gestionticket.Entities.PanierItem;
import com.example.gestionticket.Entities.Evenement;
import org.springframework.stereotype.Service;

@Service
public interface PanierService {

    Panier getPanierByUser(Long userId);
    PanierItem addEvenementToPanier(Long panierId, Evenement evenement, int quantity);
    void removeEvenementFromPanier(Long panierId, Long evenementId);
    double calculateTotalPrice(Long panierId);
}