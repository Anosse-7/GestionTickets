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
    private EvenementRepository eventRepository;
    private Model model;

    @Override
    public List<Evenement> listAll(){
        return (List<Evenement>) eventRepository.findAll();
    }

    @Override
    public Evenement getCurrentEvent(Long id){
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Evenement addEvent(Evenement evenement){

        return eventRepository.save(evenement);
    }

    @Override
    public Evenement updateEvent(Evenement evenement){
        if (eventRepository.existsById(evenement.getId())){
            return eventRepository.save(evenement);
        }
        return null;
    }


}