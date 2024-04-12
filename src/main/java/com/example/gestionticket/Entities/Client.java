package com.example.gestionticket.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Client extends Perssone {
    @Id
    @GeneratedValue
    private int id;
}
