package com.example.gestionticket.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 50,unique = true,name = "username")
    private String username;

    @Column(nullable = false,length = 50,name = "nom")
    private String nom;

    @Column(nullable = false,length = 50, name = "prenom")
    private String prenom;

    @Column
    private String Gender;

    @Column(nullable = false,length = 50,unique = true,name = "email")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(length = 50,name = "ville")
    private String ville;

    @Column(length = 50,name = "telephone")
    private String telephone;

    @Column(name = "avatar")
    @Lob
    private byte[] avatar;

    @Column(nullable = false,name = "active")
    private boolean active;

    @Column(nullable = false,name = "role")
    private String role;


    public User(String username ,String nom, String prenom, String email, String password, String ville, String telephone, byte[] avatar, boolean active , String role) {

        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.ville = ville;
        this.telephone = telephone;
        this.active = active;
        this.role = role;
        this.avatar = avatar;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement your logic based on account expiration, if applicable
    }

    @Override
    public boolean isAccountNonLocked() {
        return active; // Implement your logic based on account locking, if applicable
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic based on credential expiration, if applicable
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
