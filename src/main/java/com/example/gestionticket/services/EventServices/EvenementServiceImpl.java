package com.example.gestionticket.services.EventServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.EvenementRepository;
import com.example.gestionticket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service("evenementServiceImpl")
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    private EvenementRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
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
    public Evenement updateEvent(Evenement evenement) {
        if (eventRepository.existsById(evenement.getId())) {
            return eventRepository.save(evenement);
        }
        return null;
    }

    @Override
    public Evenement updateEvenement(Evenement newEvenement) {
        Optional<Evenement> oldEvenementOpt = eventRepository.findById(newEvenement.getId());

        if (oldEvenementOpt.isPresent()) {
            Evenement oldEvenement = oldEvenementOpt.get();

            if (newEvenement.getTitre() != null && !newEvenement.getTitre().equals(oldEvenement.getTitre())) {
                oldEvenement.setTitre(newEvenement.getTitre());
            }

            if (newEvenement.getDateEvenement() != null && !newEvenement.getDateEvenement().equals(oldEvenement.getDateEvenement())) {
                oldEvenement.setDateEvenement(newEvenement.getDateEvenement());
            }

            if (newEvenement.getTypeEvenement() != null && !newEvenement.getTypeEvenement().equals(oldEvenement.getTypeEvenement())) {
                oldEvenement.setTypeEvenement(newEvenement.getTypeEvenement());
            }

            if (newEvenement.getDescription() != null && !newEvenement.getDescription().equals(oldEvenement.getDescription())) {
                oldEvenement.setDescription(newEvenement.getDescription());
            }

            if (newEvenement.getPhoto() != null && !newEvenement.getPhoto().equals(oldEvenement.getPhoto())) {
                oldEvenement.setPhoto(newEvenement.getPhoto());
            }

            return eventRepository.save(oldEvenement);
        } else {
            throw new RuntimeException("Event not found with id: " + newEvenement.getId());
        }
    }

    @Override
    public List<Evenement> findEventsByUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return eventRepository.findByUser(user);
        } else {
            return null;
        }
    }
}