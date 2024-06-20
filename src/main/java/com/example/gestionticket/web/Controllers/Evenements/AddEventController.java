package com.example.gestionticket.web.Controllers.Evenements;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.UserRepository;
import com.example.gestionticket.services.EventServices.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/addEvent")
public class AddEventController {

    private final EvenementService eventService;
    private final UserRepository userRepo;

    @Autowired
    public AddEventController(@Qualifier("evenementServiceImpl") EvenementService eventService, UserRepository userRepo) {
        this.eventService = eventService;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String showEventForm(Model model) {
        model.addAttribute("evenement", new Evenement());
        return "Event/addEvent"; // Assuming this is the correct path to your view template
    }

    @PostMapping
    public String addEvent(@ModelAttribute("evenement") @Valid Evenement evenement,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("photo") MultipartFile photo) {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = auth.getName();
            User currentUser = userRepo.findByUsername(currentUsername);

            evenement.setCreatedBy(currentUser);

            eventService.addEvent(evenement, photo);
            redirectAttributes.addFlashAttribute("success", "Event added successfully");
            return "redirect:/addEvent?success=true";
        } catch (IllegalArgumentException e) {
            result.rejectValue("photo", "error.photo", e.getMessage());
            System.out.println(e.getMessage());
        } catch (IOException e) {
            result.rejectValue("photo", "error.photo", "Error while saving the photo");
            System.out.println(e.getMessage());
        }
        return "redirect:/adminEvents";
    }
}