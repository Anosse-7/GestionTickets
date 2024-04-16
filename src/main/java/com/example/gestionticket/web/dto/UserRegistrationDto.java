package com.example.gestionticket.web.dto;

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

}

