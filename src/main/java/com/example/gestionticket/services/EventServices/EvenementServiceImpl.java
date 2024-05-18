package com.example.gestionticket.services.EventServices;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
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
    public List<Evenement> listAll() {
        return (List<Evenement>) eventRepository.findAll();
    }

    @Override
    public Evenement getCurrentEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }


    @Override
    public String addEvent(Evenement evenement, MultipartFile photo) throws IOException {
        // Save the photo and get the path
        String photoPath = saveEventImage(evenement, photo);
        evenement.setPhoto(photoPath);
        // Save the event
        eventRepository.save(evenement);
        return photoPath;
    }

    @Override
    public String saveEventImage(Evenement event, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // Save the image file to a permanent location
            String fileName = file.getOriginalFilename();
            String directory = "src/main/resources/static/assets/eventImg";
            String filePath = Paths.get(directory, fileName).toString();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();

            // Store the relative path to the image file in the database
            return "/eventImgs/" + fileName;
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
    public Optional<Evenement> findById(Long id) {
        return eventRepository.findById(id);
    }
}