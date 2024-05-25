package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.Evenement;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("EvenementRepository")
public interface EvenementRepository extends JpaRepository<Evenement, Long> {

}
