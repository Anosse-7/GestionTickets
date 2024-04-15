package com.example.gestionticket.services;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.web.dto.UserRegistrationDto;

public interface UserService {

    User save(UserRegistrationDto registrationDto);
}
