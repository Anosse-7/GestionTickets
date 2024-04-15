package com.example.gestionticket.web;

import com.example.gestionticket.services.UserService;
import com.example.gestionticket.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserResgistrationController {

    private UserService userService;

    public UserResgistrationController(UserService userService) {
        this.userService = userService;
    }

    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto){
       userService.save(registrationDto);

       return "redirect:/registration?success";
    }
}
