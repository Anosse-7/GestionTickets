package com.example.gestionticket.web.Controllers;
import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EvenementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/Evenement")
public class EvenementController {

    private final EvenementServiceImpl evenementService;

    @Autowired
    public EvenementController(EvenementServiceImpl evenementServiceImpl) {
        this.evenementService = evenementServiceImpl;
    }

    @GetMapping("/{eventId}")
    public String Event(@PathVariable Long eventId, Model model) {
        if(!Objects.equals(eventId, evenementService.getCurrentEvent(eventId).getId())){
            return "redirect:/noEvent";
        }
        Evenement currentEvent = evenementService.getCurrentEvent(eventId);
        model.addAttribute("currentEvent", currentEvent);
        return "Event/Evenement";
    }
}