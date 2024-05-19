package com.example.gestionticket.services.TicketsServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.Repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{


    @Autowired
    private EvenementRepository eventRepository;

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
    public void addTicketsToEvent(Long eventId, Ticket ticket, MultipartFile photo) throws IOException {
        Optional<Evenement> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Evenement event = optionalEvent.get();
            event.getTickets().add(ticket);
            // Store the photo and set its path to the ticket
            String photoPath = saveTicketImage(photo);
            ticket.setPhoto(photoPath);
            eventRepository.save(event);
        } else {
            throw new RuntimeException("Event with id " + eventId + " does not exist");
        }
    }
}
