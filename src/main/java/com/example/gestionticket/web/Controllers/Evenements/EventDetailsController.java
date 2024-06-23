package com.example.gestionticket.web.Controllers.Evenements;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EventServices.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventDetailsController {

    @Autowired
    private EvenementService evenementService;

    @GetMapping("/eventDetails")
    public String showEventDetails(@RequestParam("id") Long id, Model model) {
        Evenement evenement = evenementService.getEventById(id);
        model.addAttribute("event", evenement);
        model.addAttribute("tickets", evenement.getTickets());
        return "Event/eventDetails";
    }
}
