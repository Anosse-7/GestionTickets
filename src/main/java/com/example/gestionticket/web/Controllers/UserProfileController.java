package com.example.gestionticket.web.Controllers;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.UserRepository;
import com.example.gestionticket.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showUserProfile(Model model) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(currentUserName);
        model.addAttribute("update", currentUser);
        return "Interfaces/userProfile";
    }

    @PostMapping
    public String updateUserProfile(@ModelAttribute("update") User updatedUser, Model model) {
        try {
            userService.updateUserProfile(updatedUser);
            model.addAttribute("update", updatedUser);
            return "Interfaces/userProfile";

        } catch (RuntimeException e) {
            model.addAttribute("error", "Error updating user profile: " + e.getMessage());
            return "Interfaces/userProfile";
        }
    }
}