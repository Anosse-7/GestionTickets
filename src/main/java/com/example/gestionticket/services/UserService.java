package com.example.gestionticket.services;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
}
