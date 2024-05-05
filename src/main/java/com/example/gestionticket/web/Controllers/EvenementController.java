package com.example.gestionticket.web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.PrivateKey;

@Controller
@RequestMapping("/Evenement")
public class EvenementController {
    @GetMapping
    public String Event(){
        System.out.println("Evenement Controller");
        return "/Event/Evenement";
    }
}
