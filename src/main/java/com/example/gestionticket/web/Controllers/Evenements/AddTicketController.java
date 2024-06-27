package com.example.gestionticket.web.Controllers.Evenements;

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

@Controller
public class AddTicketController {

    private final TicketService ticketService;

    public AddTicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/addTicket/{eventId}")
    public String showAddTicketForm(@PathVariable("eventId") Long eventId, Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("eventId", eventId);
        return "Event/addTicket";
    }

    @PostMapping("/addTicket/{eventId}")
    public String addTicket(@Valid Ticket ticket, BindingResult bindingResult,
                            @PathVariable("eventId") Long eventId,
                            @RequestParam("file") MultipartFile file, // Add this line
                            RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println("Binding errors: " + bindingResult.getAllErrors());
            return "redirect:/addTicket?error=true";
        }
        try {
            ticketService.addTicketToEvent(eventId, ticket, file); // Pass the file to the method
            redirectAttributes.addFlashAttribute("success", "Ticket added successfully!");
            return "redirect:/adminTickets/" + eventId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while adding the ticket: " + e.getMessage());
            return "Event/addTicket";
        }
    }
}

