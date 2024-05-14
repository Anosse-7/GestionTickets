package com.example.gestionticket.web.Controllers;

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
        String imagePath;
        if (currentUser.getProfileImage() == null || currentUser.getProfileImage().isEmpty()) {
            // Set the image path to the default image if the user's profile image is null or empty
            imagePath = "/ProfileImgs/user.png";
        } else {
            imagePath = currentUser.getProfileImage();
        }
        System.out.println("Current User: " + currentUserName);
        System.out.println("Image Path: " + imagePath);
        model.addAttribute("imagePath", imagePath);
        model.addAttribute("update", currentUser);
        return "Interfaces/userProfile";
    }

    @PostMapping
    public String upDateProfile(@ModelAttribute("update") User updateUser,
                                @RequestParam("image") MultipartFile file,
                                @RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("countryCode") String countryCode,
                                Model model) {

        if (!phoneNumber.isEmpty() && !phoneNumberService.isValidPhoneNumber(phoneNumber, countryCode)) {
            model.addAttribute("error", "Invalid phone number");
            return "Interfaces/userProfile";
        }

        try {
            userService.saveProfileImage(updateUser, file);
            userService.updateUserProfile(updateUser);

            // Retrieve the updated user from the database
            User updatedUser = userRepository.findById(updateUser.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Retrieve the image path from the updated user
            String imagePath;
            if (updatedUser.getProfileImage() == null || updatedUser.getProfileImage().isEmpty()) {
                // Set the image path to the default image if the user's profile image is null or empty
                imagePath = "/ProfileImgs/user.png";
                System.out.println("Image Path default: " + imagePath);
            } else {
                imagePath = updatedUser.getProfileImage();
                System.out.println("Image Path accepted: " + imagePath);
            }

            // Add the image path to the model
            model.addAttribute("imagePath", imagePath);

            model.addAttribute("success", "Profile updated successfully");
            return "Interfaces/userProfile";
        } catch (IOException e) {
            model.addAttribute("error", "Error Updating User Profile image: " + e.getMessage());
            return "Interfaces/userProfile";
        }
    }

}
