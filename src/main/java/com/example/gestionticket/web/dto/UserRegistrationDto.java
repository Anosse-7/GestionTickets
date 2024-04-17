package com.example.gestionticket.web.dto;

import com.example.gestionticket.Entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRegistrationDto {

    private String username;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String repeatPassword;
    private String addresse;
    private String telephone;
    private boolean active;

    public UserRegistrationDto() {

    }
    // Add this method to the UserRegistrationDto class
public User toUser() {
    User user = new User();
    user.setUsername(this.username);
    user.setNom(this.nom);
    user.setPrenom(this.prenom);
    user.setEmail(this.email);
    user.setPassword(this.password);
    user.setAddresse(this.addresse);
    user.setTelephone(this.telephone);
    user.setActive(this.active);
    return user;
}

    public String getRepeatPassword() {
        return password;
    }
}

