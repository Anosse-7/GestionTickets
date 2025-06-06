package com.example.gestionticket.services.TicketsServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.Repository.EvenementRepository;
import com.example.gestionticket.Repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class TicketServiceImpl implements TicketService{


    @Autowired
    private EvenementRepository eventRepository;

    @Autowired
    private TicketsRepository ticketRepository;

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
}
