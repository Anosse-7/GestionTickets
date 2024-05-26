package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository("EvenementRepository")
public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    List<Evenement> findByUser(User user);
}
