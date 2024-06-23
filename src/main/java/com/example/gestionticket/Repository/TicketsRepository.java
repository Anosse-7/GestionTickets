package com.example.gestionticket.Repository;


import com.example.gestionticket.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByEvenementId(Long eventId);

}
