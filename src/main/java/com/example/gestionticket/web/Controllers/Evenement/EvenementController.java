package com.example.gestionticket.web.Controllers.Evenement;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EvenementController {

    private final EvenementRepository eventRepo;

    @Autowired
    public EvenementController(@Qualifier("EvenementRepository") EvenementRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    @GetMapping("/Evenement")
    public String showEvents(Model model) {
        List<Evenement> events = eventRepo.findAll();
        if (events.isEmpty()) {
            return "Event/noEvent";
        } else {
            model.addAttribute("events", events);
            return "Event/Evenement";
        }
    }
}
