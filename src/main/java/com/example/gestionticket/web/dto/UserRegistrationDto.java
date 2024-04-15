package com.example.gestionticket.web.dto;

public class UserRegistrationDto {

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String address;
    private String telephone;
    private boolean active;


    public UserRegistrationDto(String nom, String prenom, String email, String password, String address, String telephone, boolean active) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
        this.active = active;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

