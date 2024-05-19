package com.example.gestionticket.services.TicketsServices;

import com.example.gestionticket.Entities.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("ticketService")
public interface TicketService {

    String saveTicketImage(MultipartFile file) throws IOException;

    void addTicketsToEvent(Long eventId, Ticket ticket, MultipartFile photo) throws IOException;
}
