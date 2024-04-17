package com.example.gestionticket.web;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.services.UserService;
import com.example.gestionticket.web.Controllers.UserRegistrationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
    private static final Logger log = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    public UserRegistrationController(UserService userService){
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "Registration/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto registrationDto, BindingResult result) {
        User existingUser = userService.findByUsername(registrationDto.getUsername());
        if (existingUser != null) {
            result.rejectValue("username", null, "There is already an account registered with that username");
        }

        if (!registrationDto.getPassword().equals(registrationDto.getRepeatPassword())) {
            result.rejectValue("repeatPassword", null, "Passwords don't match");
        }

        if (result.hasErrors()) {
            return "Registration/registration";
        }

        User user = registrationDto.toUser();
        User savedUser = userService.save(user);
        if (savedUser == null) {
            logger.error("User could not be saved");
            result.rejectValue("username", null, "User could not be saved");
            return "Registration/registration";
        }
        return "redirect:/registration?success";
    }
}