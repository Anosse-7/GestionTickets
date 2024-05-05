package com.example.gestionticket.web.Controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.example.gestionticket.Entities.User;
import com.example.gestionticket.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/resetPassword")
public class ResetPasswordController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showResetPasswordForm(@ModelAttribute("user") User user,
                                        @RequestParam(value = "error", required = false) String error,
                                        @RequestParam(value = "success", required = false) String success,
                                        Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        if (success != null) {
            model.addAttribute("success", success);
        }
        return "Interfaces/resetPassword";
    }

    @PostMapping
    public String resetPassword(@RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.findByUsername(username);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "redirect:/resetPassword?error=User not found";
        }
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("error", "Old password is incorrect");
            return "redirect:/resetPassword?error=Old password is incorrect";
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "New password and confirm password do not match");
            return "redirect:/resetPassword?error=New password and confirm password do not match";
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.save(user);
        model.addAttribute("success", "Password has been reset successfully");
        return "redirect:/resetPassword?success=Password has been reset successfully";
    }
}
