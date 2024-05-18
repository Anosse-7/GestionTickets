package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.EvenementRepository;
import com.example.gestionticket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service("evenementServiceImpl")
public class EvenementServiceImpl implements EvenementService{

    @Autowired
    private EvenementRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepository.findByUsername(currentPrincipalName); // Assuming you have a method to get user by username

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