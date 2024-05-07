package com.example.gestionticket.web.Controllers.UserManagement;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.UserRepository;
import com.example.gestionticket.services.PhoneNumberService;
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
import java.util.Base64;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping
    public String showUserProfile(Model model) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(currentUserName);
        String imageBase64;
        if (currentUser.getProfileImage() != null) {
            imageBase64 = Base64.getEncoder().encodeToString(currentUser.getProfileImage());
        } else {
            imageBase64 = null;
        }
        model.addAttribute("imageBase64", imageBase64);
        model.addAttribute("update", currentUser);
        return "Interfaces/userProfile";
    }

    @PostMapping
    public String upDateProfile(@ModelAttribute("update") User updateUser,
                                     @RequestParam("image") MultipartFile file, Model model) {

        if (!updateUser.getTelephone().isEmpty() && !phoneNumberService.isValidPhoneNumber(updateUser.getTelephone(), updateUser.getCountryCode())) {
            model.addAttribute("error", "Invalid phone number");
            return "Interfaces/userProfile";
        }

        try {
            userService.saveProfileImage(updateUser, file);
            userService.updateUserProfile(updateUser);
            model.addAttribute("success", "Profile updated successfully");
            return "Interfaces/userProfile";
        } catch (IOException e) {
            model.addAttribute("error", "Error Updating User Profile image: " + e.getMessage());
            return "Interfaces/userProfile";
        }
    }

}
