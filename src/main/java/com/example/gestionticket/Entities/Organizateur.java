package com.example.gestionticket.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Organizateur extends Perssone{
    @Id
    @GeneratedValue
    private int oganizateur_id;
}
