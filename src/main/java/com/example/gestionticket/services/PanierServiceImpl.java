// PanierServiceImpl.java
package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Panier;
import com.example.gestionticket.Entities.PanierItem;
import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Repository.PanierRepository;
import com.example.gestionticket.Repository.PanierItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final PanierItemRepository panierItemRepository;

    @Autowired
    public PanierServiceImpl(PanierRepository panierRepository, PanierItemRepository panierItemRepository) {
        this.panierRepository = panierRepository;
        this.panierItemRepository = panierItemRepository;
    }

    @Override
    public Panier getPanierByUser(Long userId) {
        // Assuming that the Panier is linked to the User entity
        return panierRepository.findByUserId(userId);
    }

    @Override
    public PanierItem addEvenementToPanier(Long panierId, Evenement evenement, int quantity) {
        PanierItem panierItem = new PanierItem();
        panierItem.setPanier(new Panier(panierId));
        panierItem.setEvenement(evenement);
        panierItem.setQuantity(quantity);
        panierItem.setPrice(evenement.getPrice() * quantity); // Assuming that the Evenement entity has a getPrice() method
        return panierItemRepository.save(panierItem);
    }

    @Override
    public void removeEvenementFromPanier(Long panierId, Long evenementId) {
        Optional<PanierItem> panierItem = panierItemRepository.findByPanierIdAndEvenementId(panierId, evenementId);
        panierItem.ifPresent(panierItemRepository::delete);
    }

    @Override
    public double calculateTotalPrice(Long panierId) {
        return panierItemRepository.findByPanierId(panierId).stream()
                .mapToDouble(PanierItem::getPrice)
                .sum();
    }
}