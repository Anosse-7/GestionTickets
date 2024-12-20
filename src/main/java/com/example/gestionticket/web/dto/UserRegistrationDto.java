package com.example.gestionticket.web.dto;

import com.example.gestionticket.Entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRegistrationDto {

    private Long id;
    private String username;
    private String nom;
    private String prenom;
    private String Gender;
    private String email;
    private String password;
    private String repeatPassword;
    private String ville;
    private String telephone;
    private String profileImage;
    private boolean active;
    private String role;

    public UserRegistrationDto() {

    }
    public User toUser() {
    User user = new User();
    user.setId(this.id);
    user.setUsername(this.username);
    user.setNom(this.nom);
    user.setPrenom(this.prenom);
    user.setGender(this.Gender);
    user.setEmail(this.email);
    user.setPassword(this.password);
    user.setVille(this.ville);
    user.setTelephone(this.telephone);
    user.setProfileImage(this.profileImage);
    user.setActive(this.active);
    if (this.role == null || this.role.isEmpty()) {
        user.setRole("Client"); // replace "DEFAULT_ROLE" with your default role
    } else {
        user.setRole(this.role);
    }
    return user;
}

    public String getRepeatPassword() {
        return password;
    }
}

