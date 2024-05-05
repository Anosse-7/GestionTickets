package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class EvenementServiceImpl implements EvenementService{

    @Autowired
    private EvenementRepository event;
    private Model model;

    @Override
    public List<Evenement> listAll(){
        return (List<Evenement>) event.findAll();
    }

    @Override
    public Evenement getCurrentEvent(Long id){
        return event.findById(id).orElse(null);
    }

    @Override
    public Evenement addEvent(Evenement evenement){
        return event.save(evenement);
    }

    @Override
    public Evenement updateEvent(Evenement evenement){
        if (event.existsById(evenement.getId())){
            return event.save(evenement);
        }
        return null;
    }


}