package com.example.gestionticket.services.TicketsServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.Repository.EvenementRepository;
import com.example.gestionticket.Repository.TicketsRepository;
import com.example.gestionticket.services.EventServices.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service("ticketServiceImpl")
public class TicketServiceImpl implements TicketService{


    @Autowired
    private EvenementRepository eventRepository;

    @Autowired
    private  TicketsRepository ticketRepository;

    private Ticket ticket;


    @Override
    public String saveTicketImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // Save the image file to a permanent location
            String fileName = file.getOriginalFilename();
            String directory = "src/main/resources/static/assets/ticketsImg";
            String filePath = Paths.get(directory, fileName).toString();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();

            // Store the relative path to the image file in the database
            return "/ticketsImg/" + fileName;
        }
        return null;
    }

    @Override
    public void addTicketToEvent(Long eventId, Ticket ticket, MultipartFile file) throws IOException {
        Evenement evenement = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event with id " + eventId + " does not exist"));

        String photoPath = saveTicketImage(file); // Save the image file and get the photo path
        ticket.setPhoto(photoPath); // Set the photo path on the Ticket

        ticket.setEvenement(evenement); // Set the Event on the Ticket
        evenement.getTickets().add(ticket); // Add the Ticket to the Event's list of tickets

        ticketRepository.save(ticket); // Save the Ticket
        eventRepository.save(evenement); // Save the Event
    }

@Override
public void UpdateTicket(Ticket currentTicket) {
    if (ticketRepository.existsById(currentTicket.getId())){
        Ticket ticket = ticketRepository.findById(currentTicket.getId()).orElse(null);
        assert ticket != null;
        if(!Objects.equals(currentTicket.getTitre(),ticket.getTitre())){
            ticket.setTitre(currentTicket.getTitre());
        }
        if(!Objects.equals(currentTicket.getDescription(),ticket.getDescription())){
            ticket.setDescription(currentTicket.getDescription());
        }
        if(!Objects.equals(currentTicket.getPrix(),ticket.getPrix())){
            ticket.setPrix(currentTicket.getPrix());
        }
        if(!Objects.equals(currentTicket.getQuantite(),ticket.getQuantite())){
            ticket.setQuantite(currentTicket.getQuantite());
        }
        if(!Objects.equals(currentTicket.getPhoto(),ticket.getPhoto())){
            ticket.setPhoto(currentTicket.getPhoto());
        }
        ticketRepository.save(ticket);
    }
}
    @Override
    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
