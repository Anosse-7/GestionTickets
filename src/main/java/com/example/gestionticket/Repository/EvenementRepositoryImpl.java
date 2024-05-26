package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.Evenement;
import com.example.gestionticket.Entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class EvenementRepositoryImpl implements EvenementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Evenement> findByUser(User user) {
        TypedQuery<Evenement> query = entityManager.createQuery(
                "SELECT e FROM Evenement e WHERE e.user = :user", Evenement.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

}
