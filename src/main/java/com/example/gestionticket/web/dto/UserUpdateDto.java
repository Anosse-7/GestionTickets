package com.example.gestionticket.web.dto;

import com.example.gestionticket.Entities.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserUpdateDto {

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
    private byte[] avatar;
    private boolean active;
    private String role;

    public UserUpdateDto() {
    }

    public User toUserUpdate() {

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
        user.setAvatar(this.avatar);
        user.setActive(this.active);
        user.setRole(this.role);

        return user;
    }
}
