package com.example.gestionticket.Entities;

import com.example.gestionticket.Validator.ConfirmPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,length = 50,unique = true,name = "username")
    private String username;

    @Column(nullable = false,length = 50,name = "nom")
    private String nom;

    @Column(nullable = false,length = 50, name = "prenom")
    private String prenom;

    @Column(nullable = false,length = 50,unique = true,name = "email")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Repeat password cannot be blank")
    @ConfirmPassword(field = "password") // Custom annotation for confirmation
    private String repeatPassword;

    @Column(nullable = false,length = 50,name = "adresse")
    private String addresse;

    @Column(nullable = false,length = 50,name = "telephone")
    private String telephone;


    @Column(nullable = false,name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id" , referencedColumnName = "id"))
    private Collection<Role> roles = Collections.singletonList(new Role("Client"));;

    public User(String username ,String nom, String prenom, String email, String password,String repeatPassword , String addresse, String telephone, boolean active, Collection<Role> roles) {

        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.addresse = addresse;
        this.telephone = telephone;
        this.active = active;
        this.roles = roles;
        if (roles == null || roles.isEmpty()) {
            this.roles = Collections.singletonList(new Role("Client"));
        } else {
            this.roles = roles;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
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
