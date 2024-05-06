package com.example.gestionticket.web.Controllers;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.services.EvenementService;
import jdk.jfr.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addEvent")
public class AddEventController {

    private EvenementService eventService = null;
    @GetMapping
    public String returnEvent(){
        return "Event/addEvent";
    }

    @PostMapping
    public String addEvent(@ModelAttribute("addEvent") Evenement currentEvent){
        if(currentEvent.getId() != null){
            eventService.getCurrentEvent(currentEvent.getId());
        }
        return "Event/addEvent";
    }
}
