package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String role_name;

    public Role(String role_name) {
        this.role_name = role_name;
    }
}
