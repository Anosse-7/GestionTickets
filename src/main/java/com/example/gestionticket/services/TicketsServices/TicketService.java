package com.example.gestionticket.services.TicketsServices;

import com.example.gestionticket.Entities.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("ticketService")
public interface TicketService {
    void addTicketsToEvent(Long eventId, List<Ticket> tickets, MultipartFile photo);
}
