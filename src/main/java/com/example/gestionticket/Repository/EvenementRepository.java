package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.Evenement;
import jdk.jfr.Event;
import org.springframework.data.repository.CrudRepository;

public interface EvenementRepository extends CrudRepository<Evenement, Integer> {
}
