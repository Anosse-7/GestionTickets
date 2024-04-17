package com.example.gestionticket.web;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.UserRepository;
import com.example.gestionticket.services.UserService;
import com.example.gestionticket.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserResgistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserService userService;
    private final UserRepository userRepository;

    public UserResgistrationController(UserService userService, UserRepository userRepository) {
        super();
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(UserService userService){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "Registration/registration.html";
    }// Add this method to the UserRegistrationDto class

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) {
    User existingUser = userRepository.findByEmail(registrationDto.getEmail());
    if (existingUser != null) {
        result.rejectValue("email", null, "There is already an account registered with that email");
    }

    if (!passwordEncoder.matches(registrationDto.getPassword(), registrationDto.getRepeatPassword())) {
        result.rejectValue("repeatPassword", null, "Passwords don't match");
    }

    if (result.hasErrors()) {
        return "Registration/registration"; // Return to registration page with error messages
    }

    User user = registrationDto.toUser(); // Convert the DTO to a User entity
    userRepository.save(user); // Save the User entity
    return "redirect:/registration?success";
}
}
