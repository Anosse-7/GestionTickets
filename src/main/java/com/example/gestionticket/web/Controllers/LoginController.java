package com.example.gestionticket.web.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
        public String loginGetHandller () {
            return "Login/login";
        }


    @PostMapping("/login")
    public String loginPostHandller(){
        Authentication authentification = SecurityContextHolder.getContext().getAuthentication();
        if (authentification.isAuthenticated()) {
            return "redirect:/index";
        }
        return "redirect:/login";
    }

}
