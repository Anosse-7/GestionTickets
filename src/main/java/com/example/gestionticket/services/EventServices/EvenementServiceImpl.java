package com.example.gestionticket.services.EventServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.Repository.EvenementRepository;
import com.example.gestionticket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service("evenementServiceImpl")
public class EvenementServiceImpl implements EvenementService {

    @Qualifier("EvenementRepository")
    @Autowired
    private EvenementRepository eventRepository;
    @Qualifier("UserRepository")
    @Autowired
    private UserRepository userRepository;
    private Evenement evenement;
    private Model model;


    @Override
    public String addEvent(Evenement evenement, MultipartFile photo) throws IOException {
        // Save the photo and get the path
        String photoPath = saveEventImage(photo);
        evenement.setPhoto(photoPath);
        // Save the event
        eventRepository.save(evenement);
        return photoPath;
    }

    @Override
    public Evenement getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public String saveEventImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // Save the image file to a permanent location
            String fileName = file.getOriginalFilename();
            String directory = "src/main/resources/static/assets/eventImg";
            String filePath = Paths.get(directory, fileName).toString();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();

            // Store the relative path to the image file in the database
            return "/eventImg/" + fileName;
        }
        return null;
    }

    @Override
    public Evenement updateEvent(Evenement currentEvenement) {
        if (eventRepository.existsById(currentEvenement.getId())) {
            Evenement evenement = eventRepository.findById(currentEvenement.getId()).orElse(null);
            assert evenement != null;
            if(!Objects.equals(currentEvenement.getTitre(), evenement.getTitre()))
                evenement.setTitre(currentEvenement.getTitre());
            if(!Objects.equals(currentEvenement.getDescription(), evenement.getDescription()))
                evenement.setDescription(currentEvenement.getDescription());
            if(!Objects.equals(currentEvenement.getDateEvenement(), evenement.getDateEvenement()))
                evenement.setDateEvenement(currentEvenement.getDateEvenement());
            if(!Objects.equals(currentEvenement.getPhoto(), evenement.getPhoto()))
                evenement.setPhoto(currentEvenement.getPhoto());
            if(!Objects.equals(currentEvenement.getTypeEvenement(), evenement.getTypeEvenement()))
                evenement.setTypeEvenement(currentEvenement.getTypeEvenement());
            return eventRepository.save(evenement);
        }
        return null;
    }

    @Override
    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    @Override
    public List<Ticket> getEventTicketById(Long id) {
        Evenement event = eventRepository.findById(id).orElse(null);
        assert event != null;
        return event.getTickets();
    }

}