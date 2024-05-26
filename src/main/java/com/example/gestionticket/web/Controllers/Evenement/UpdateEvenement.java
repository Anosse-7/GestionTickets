package com.example.gestionticket.web.Controllers.Evenement;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EventServices.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/EvenementManager")
public class UpdateEvenement {

    @Autowired
    private EvenementService evenementService;

    @PostMapping("/Evenement")
    public String updateEvenement(@ModelAttribute Evenement newEvenement, Model model) {
        Evenement updatedEvenement = evenementService.updateEvenement(newEvenement);
        model.addAttribute("updateEvenement", updatedEvenement);
        return "Event/EvenementManager"; // return the name of the view
    }
}
