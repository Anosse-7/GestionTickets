package com.example.gestionticket.web.Controllers.Evenement;

import com.example.gestionticket.Entities.Ticket;
import com.example.gestionticket.services.TicketsServices.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class AddTicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/addTicket")
    public String showAddTicketForm(Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        return "Event/addTicket";
    }

    @PostMapping("/addTicket")
    public String addTicket(@ModelAttribute("ticket") Ticket ticket,
                            @RequestParam("eventId") Long eventId,
                            @RequestParam("tickets") List<Ticket> tickets,
                            @RequestParam("photo") MultipartFile photo) {

        ticketService.addTicketsToEvent(eventId, tickets, photo);
        return "redirect:/events";
    }
}
