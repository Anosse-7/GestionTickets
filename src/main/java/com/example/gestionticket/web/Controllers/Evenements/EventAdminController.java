package com.example.gestionticket.web.Controllers.Evenements;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.EvenementRepository;
import com.example.gestionticket.Repository.UserRepository;
import com.example.gestionticket.services.EventServices.EvenementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class EventAdminController {

    private final EvenementRepository eventRepo;
    private final UserRepository userRepo;
    private final EvenementService eventService;

    @Autowired
    public EventAdminController(@Qualifier("EvenementRepository") EvenementRepository eventRepo, @Qualifier("UserRepository") UserRepository userRepo, @Qualifier("evenementServiceImpl") EvenementService eventService) {
        this.eventRepo = eventRepo;
        this.userRepo = userRepo;
        this.eventService = eventService;
    }

    @GetMapping("/adminEvents")
    public String showAdminEvents(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        User currentUser = userRepo.findByUsername(currentUsername);
        List<Evenement> events = eventRepo.findAllByCreatedBy(currentUser);
        model.addAttribute("events", events);

        // Add a default evenement object to the model
        Evenement defaultEvenement = new Evenement();
        model.addAttribute("evenement", defaultEvenement);

        return "Event/adminEvent";
    }

    @GetMapping("/adminEvents/details")
    public ResponseEntity<Evenement> getEventDetails(@RequestParam("id") Long id) {
        Evenement evenement = eventService.getEventById(id);
        if (evenement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evenement);
    }

    @PostMapping("/adminEvents/{idEvent}")
    public String updateEvenement(@PathVariable("idEvent") Long idEvent, @Valid @ModelAttribute("evenement") Evenement evenement,@RequestParam("file") MultipartFile file, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "redirect:/adminEvent?id=" + idEvent + "&error=true";
        }
        if (file != null) {
            try {
                String photoEvent = eventService.saveEventImage(file);
                System.out.println("Type of file: " + file.getClass().getName()); // Log the type of file
                System.out.println("Return value of saveEventImage: " + photoEvent); // Log the return value of saveEventImage
                evenement.setPhoto(photoEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("evenement", evenement);
        eventService.updateEvent(evenement);
        return "redirect:/adminEvents";
    }


    @DeleteMapping("/adminEvents/{id}")
    public String deleteEvenement(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/adminEvents";
    }

}