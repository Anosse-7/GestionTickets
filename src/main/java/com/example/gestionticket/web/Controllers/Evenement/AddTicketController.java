package com.example.gestionticket.web.Controllers.Evenement;

import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.services.TicketsServices.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/addTicket")
public class AddTicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String showAddTicketForm(@RequestParam("eventId") Long eventId, Model model) {
        System.out.println("Event id: " + eventId);
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("eventId", eventId);
        return "Event/addTicket";
    }

    @PostMapping("{eventId}")
    public String addTicket(@Valid Ticket ticket, BindingResult bindingResult,
                            @PathVariable("eventId") Long eventId,
                            @RequestParam("photo") MultipartFile photo,
                            RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            System.out.println("Binding errors: " + bindingResult.getAllErrors());
            return "redirect:/addTicket?error=true";
        }

        try {
            ticketService.addTicketsToEvent(eventId, ticket, photo);
            redirectAttributes.addFlashAttribute("success", "Ticket added successfully!");
            return "Event/addTicket";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while adding the ticket: " + e.getMessage());
            return "Event/addTicket";
        }
    }
}

