package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.PanierItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PanierItemRepository extends JpaRepository<PanierItem, Long> {
    List<PanierItem> findByPanierId(Long panierId);
    Optional<PanierItem> findByPanierIdAndEvenementId(Long panierId, Long evenementId);
}