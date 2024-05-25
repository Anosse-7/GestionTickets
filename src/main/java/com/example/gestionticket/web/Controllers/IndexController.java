package com.example.gestionticket.web.Controllers;

import ch.qos.logback.core.model.Model;
import com.example.gestionticket.Entities.User;
import com.example.gestionticket.services.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String indexPage (Model module) {

        return "Interfaces/index";
    }
}