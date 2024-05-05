package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementService {
    @Autowired private EvenementRepository event;

    public List<Evenement> listAll(){
return (List<Evenement>) event.findAll();
    }
}
