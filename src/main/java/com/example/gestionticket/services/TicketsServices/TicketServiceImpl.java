package com.example.gestionticket.services.TicketsServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.Repository.EvenementRepository;
import com.example.gestionticket.Repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{


    @Autowired
    private EvenementRepository eventRepository;

    @Override
    public void addTicketsToEvent(Long eventId, List<Ticket> tickets, MultipartFile photo) {
        Optional<Evenement> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Evenement event = optionalEvent.get();
            event.getTickets().addAll(tickets);
            eventRepository.save(event);
        } else {
            // Handle the case when the event does not exist
            throw new RuntimeException("Event with id " + eventId + " does not exist");
        }
    }
}
