package com.example.gestionticket.web.Controllers;
import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EvenementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Evenement")
public class EvenementController {

    private final EvenementServiceImpl evenementServiceImpl;

    @Autowired
    public EvenementController(EvenementServiceImpl evenementServiceImpl) {
        this.evenementServiceImpl = evenementServiceImpl;
    }

    @GetMapping("/{eventId}")
    public String Event(@PathVariable Long eventId, Model model) {
        Evenement currentEvent = evenementServiceImpl.getCurrentEvent(eventId);
        model.addAttribute("currentEvent", currentEvent);
        return "/Event/Evenement";
    }
}