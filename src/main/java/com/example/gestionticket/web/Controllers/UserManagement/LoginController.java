package com.example.gestionticket.web.Controllers.UserManagement;

import com.example.gestionticket.services.UserServices.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @GetMapping
        public String loginGetHandller (){
            return "Login/login";
        }
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String loginPostHandller(Model model, @RequestParam("username") String username, RedirectAttributes redirectAttributes) {
    if (!userService.userExists(username)) {
        redirectAttributes.addFlashAttribute("errorMsg", "Invalid username or password!");
        return "redirect:/login";
    }
        return "Login/login";
    }
}
