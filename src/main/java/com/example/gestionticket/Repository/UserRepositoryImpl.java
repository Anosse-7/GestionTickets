package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service("UserRepositoryImpl")
public abstract class UserRepositoryImpl implements UserRepository{
    
    User user;
    UserRepository userRepository;
    
    public User findByUsername(String username) {

        return Objects.equals(user.getUsername(), username) ? user : null;
    }
}

