package com.example.gestionticket.web.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {

    private String titre;
    private String description;
    private String photo;
    private String etat;
    private Double prix;

    public TicketDto(String titre, String description, String photo, String etat, Double prix) {
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
        this.prix = prix;
    }
}
