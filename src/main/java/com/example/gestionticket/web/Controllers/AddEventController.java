package com.example.gestionticket.web.Controllers;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/addEvent")
public class AddEventController {

    private final EvenementService eventService;

    @Autowired
    public AddEventController(@Qualifier("evenementServiceImpl") EvenementService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String showEventForm(Model model) {
        model.addAttribute("evenement", new Evenement());
        return "Event/addEvent"; // Assuming this is the correct path to your view template
    }

    @PostMapping
    public String addEvent(@ModelAttribute("evenement") @Valid Evenement evenement,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Event/addEvent"; // Return the view name directly
        }

        eventService.addEvent(evenement);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/addEvent"; // Redirect to the GET mapping to display form again
    }
}
