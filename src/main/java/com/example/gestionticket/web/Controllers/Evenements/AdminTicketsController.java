package com.example.gestionticket.web.Controllers.Evenements;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.services.EventServices.EvenementService;
import com.example.gestionticket.services.TicketsServices.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminTicketsController {

    private final TicketService ticketService;
    private final EvenementService evenementService;

    public AdminTicketsController(@Qualifier("ticketServiceImpl") TicketService ticketService, @Qualifier("evenementServiceImpl") EvenementService evenementService) {
        this.ticketService = ticketService;
        this.evenementService = evenementService;
    }

    @GetMapping("/adminTickets/{eventId}")
    public String loadTicketsForEvent(@PathVariable("eventId") Long eventId, Model model) {
        Evenement evenement = evenementService.getEventById(eventId);
        if (evenement != null) {
            model.addAttribute("tickets", evenement.getTickets());
            model.addAttribute("Ticket", new Ticket()); // For the form
        }
        return "Event/adminTickets";
    }

    @GetMapping("/adminTickets/ticket/{idTicket}")
    public String loadTicket(@PathVariable("idTicket") Long ticketId, Model model) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket != null) {
            model.addAttribute("Ticket", ticket);
        }
        return "Event/adminTickets";
    }

    @PostMapping("/adminEvents/{idTicket}")
    public String updateTicket(@PathVariable("idTicket") Long idEvent, @Valid @ModelAttribute("Ticket") Ticket ticket, @RequestParam("file") MultipartFile file, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "redirect:/adminTickets?id=" + idEvent + "&error=true";
        }
        if (file != null && !file.isEmpty()) {
            try {
                String photoEvent = ticketService.saveTicketImage(file);
                ticket.setPhoto(photoEvent);
            } catch (Exception e) {
                model.addAttribute("fileError", "Error occurred while saving the file. Please try again.");
                return "Event/adminTickets";
            }
        }

        try {
            ticketService.UpdateTicket(ticket);
        } catch (Exception e) {
            model.addAttribute("updateError", "Error occurred while updating the event. Please try again.");
            return "Event/adminTickets";
        }

        return "redirect:/adminTickets/" + idEvent + "?updated=true";
    }

}
