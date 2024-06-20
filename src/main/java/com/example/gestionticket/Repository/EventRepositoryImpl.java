package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.User;

import java.util.List;

public abstract class EventRepositoryImpl implements EvenementRepository{

        Evenement event;
        EvenementRepository eventRepository;

        public List<Evenement> findAllByCreatedBy(User user){
            return eventRepository.findAllByCreatedBy(user);
        }
}
