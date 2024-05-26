package com.example.gestionticket.services.EventServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service("evenementService")
public interface EvenementService {

    String saveEventImage(MultipartFile file) throws IOException;

    Evenement updateEvent(Evenement evenement);

    String addEvent(Evenement evenement, MultipartFile file) throws IOException;

    Evenement getEventById(Long id);

    Evenement updateEvenement(Evenement newEvenement);

    List<Evenement> findEventsByUser(String username);
}
