// PanierRepository.java
package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Long> {
    Panier findByUser_Id(Long userId);
}