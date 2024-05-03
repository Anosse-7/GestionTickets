package com.example.gestionticket.services;

import com.example.gestionticket.Entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User save(User user);

    User findByUsername(String username);

    void updateUserProfile(User updatedUser);

    boolean userExists(String username);

}
