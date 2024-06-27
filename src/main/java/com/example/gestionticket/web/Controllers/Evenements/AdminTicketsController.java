package com.example.gestionticket.web.Controllers.Evenements;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.services.EventServices.EvenementService;
import com.example.gestionticket.services.TicketsServices.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminTicketsController {

    private final TicketService ticketService;
    private final EvenementService evenementService;

    public AdminTicketsController(@Qualifier("ticketServiceImpl") TicketService ticketService, @Qualifier("evenementServiceImpl") EvenementService evenementService) {
        this.ticketService = ticketService;
        this.evenementService = evenementService;
    }

    @GetMapping("/adminTickets/{eventId}")
    public String loadTicketsForEvent(@PathVariable("eventId") Long eventId, Model model, RedirectAttributes redirectAttributes){
        Evenement evenement = evenementService.getEventById(eventId);
        if (evenement == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Event not found");
            System.out.println("Event not found");
            return "redirect:/errorPage";
        }
        List<Ticket> tickets = evenement.getTickets();
        tickets.forEach(ticket -> {

            double reducedPrice = ticket.getPrix() - (ticket.getPrix() * ticket.getReduction() / 100);
            ticket.setReducedPrice(reducedPrice);
        });
        model.addAttribute("Event", evenement);
        model.addAttribute("tickets", tickets);
        model.addAttribute("Ticket", new Ticket()); // For the form
        return "Event/adminTickets";
    }

    @GetMapping("/adminTickets/ticket/{idTicket}")
    public String loadTicket(@PathVariable("idTicket") Long ticketId, Model model) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
        model.addAttribute("Ticket", ticket);
        return "Event/adminTickets";
    }

    @GetMapping("/adminTickets/details")
    public ResponseEntity<Ticket> getTicketDetails(@RequestParam("id") Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        double reducedPrice = ticket.getPrix() - (ticket.getPrix() * ticket.getReduction() / 100);
        ticket.setReducedPrice(reducedPrice);
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/adminTickets/delete/{idTicket}")
    public String deleteTicket(@PathVariable("idTicket") Long ticketId, Model model){
        Long eventId = ticketService.getTicketById(ticketId).getEvenement().getId();
        ticketService.deleteTicket(ticketId);
        return "redirect:/adminTickets/" + eventId;
    }

    @PostMapping("/adminTickets/update/{eventId}/{idTicket}")
    public String updateTicket( @PathVariable("eventId") Long eventId,
                                @PathVariable("idTicket") Long idTicket,
                                @Valid @ModelAttribute("Ticket") Ticket ticket,
                                @RequestParam("file") MultipartFile file,
                                Model model) {
        if (file != null && !file.isEmpty()) {
            try {
                String photoEvent = ticketService.saveTicketImage(file);
                ticket.setPhoto(photoEvent);
            } catch (Exception e) {
                model.addAttribute("fileError", "Error occurred while saving the file. Please try again.");
                return "Event/adminTickets";
            }
        }
        ticket.setId(idTicket);
        System.out.println("Ticket updated successfully   ::::::::::::>>>>  " + ticket.getPhoto());
        ticketService.UpdateTicket(ticket);

        return "redirect:/adminTickets/" + eventId ;
    }
}
