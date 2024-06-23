package com.example.gestionticket.services.TicketsServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("ticketService")
public interface TicketService {

    String saveTicketImage(MultipartFile file) throws IOException;

    void addTicketToEvent(Long eventId, Ticket ticket, MultipartFile file) throws IOException;

    void UpdateTicket(Ticket ticket);

    Ticket getTicketById(Long ticketId);
}
