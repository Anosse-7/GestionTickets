package com.example.gestionticket.web.Controllers;

import com.example.gestionticket.Repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EvenementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addEvent")
public class AddEventController {

    @Qualifier("evenementService")
    @Autowired
    private EvenementService eventService;

    @GetMapping
    public String showEventForm(Model model) {
        model.addAttribute("evenement", new Evenement());
        return "/Event/addEvent";
    }


    @PostMapping
    public String addEvent(@ModelAttribute("evenement") @Valid Evenement evenement,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "/Event/addEvent";
        }

        eventService.addEvent(evenement);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/addEvent";
    }
}
