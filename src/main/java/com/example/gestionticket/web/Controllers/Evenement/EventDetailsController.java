package com.example.gestionticket.web.Controllers.Evenement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventDetails")
public class EventDetailsController {

    @GetMapping
    public String showEventDetails() {
        return "Event/eventDetails";
    }
}
