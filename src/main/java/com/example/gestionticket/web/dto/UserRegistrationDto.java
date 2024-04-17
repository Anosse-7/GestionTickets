package com.example.gestionticket.web.dto;

import com.example.gestionticket.Entities.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationDto {

    private String username;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String repeatPassword;
    private String address;
    private String telephone;
    private boolean active;

    public UserRegistrationDto() {

    }

    public UserRegistrationDto(String username,String nom, String prenom, String email, String password,String repeatPassword , String address, String telephone, boolean active) {
        super();
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.address = address;
        this.telephone = telephone;
        this.active = active;
    }

    // Add this method to the UserRegistrationDto class
public User toUser() {
    User user = new User();
    user.setUsername(this.username);
    user.setNom(this.nom);
    user.setPrenom(this.prenom);
    user.setEmail(this.email);
    user.setPassword(this.password);
    user.setRepeatPassword(this.repeatPassword);
    user.setAddresse(this.address);
    user.setTelephone(this.telephone);
    user.setActive(this.active);
    // Set the roles as needed
    return user;
}

}

