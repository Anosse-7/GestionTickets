package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Evenement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EvenementService {

    List<Evenement> listAll();
    Evenement getCurrentEvent(Long id);
    Evenement updateEvent(Evenement evenement);
    Evenement addEvent(Evenement evenement);
}
