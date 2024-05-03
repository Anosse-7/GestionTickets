package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.User;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public abstract class UserRepositoryImpl implements UserRepository{
    
    User user;
    UserRepository userRepository;
    
    public User findByUsername(String username) {

        return Objects.equals(user.getUsername(), username) ? user : null;
    }

}

