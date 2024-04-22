package com.example.gestionticket.Repository;

import com.example.gestionticket.Entities.User;

import java.util.Objects;

public abstract class UserRepositoryImpl implements UserRepository{
    
    User user;
    
    public User findByUsername(String username) {
        return Objects.equals(user.getUsername(), username) ? user : null;
    }

    public User findByEmail(String email) {
        return null;
    }

    public User findById(int id) {
        return null;
    }
}
