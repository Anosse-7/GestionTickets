package com.example.gestionticket.web.Controllers;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EvenementController {

    private final EvenementService eventService;

    @Autowired
    public EvenementController(@Qualifier("evenementServiceImpl")EvenementService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/Evenement")
    public String showEvenements(Model model) {
        List<Evenement> evenements = eventService.listAll(); // Utilisation de listAll() à la place de getAllEvenements()
        model.addAttribute("listEvenements", evenements);
        return "Event/Evenement"; // Chemin vers votre fichier evenement.html dans les ressources
    }
}
