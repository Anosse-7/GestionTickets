package com.example.gestionticket.web.Controllers.Evenement;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EventServices.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EvenementManagerController {


    private final EvenementService evenementService;

    public EvenementManagerController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping("/EvenementManager")
    public String showUpdateEvenementForm(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        List<Evenement> userEvents = evenementService.findEventsByUser(username);
        model.addAttribute("userEvents", userEvents);

        return "Event/EvenementManager"; // return the name of the view
    }
}
