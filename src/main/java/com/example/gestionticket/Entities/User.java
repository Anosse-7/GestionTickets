package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,length = 50,name = "nom")
    private String nom;

    @Column(nullable = false,length = 50, name = "prenom")
    private String prenom;

    @Column(nullable = false,length = 50,unique = true,name = "email")
    private String email;

    @Column(nullable = false,name = "Password")
    private String password;

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
    private Collection<Role> roles;

    public User(String nom, String prenom, String email, String password, String addresse, String telephone, boolean active, Collection<Role> roles) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.addresse = addresse;
        this.telephone = telephone;
        this.active = active;
        this.roles = roles;
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
