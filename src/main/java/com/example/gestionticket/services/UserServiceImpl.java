package com.example.gestionticket.services;

import com.example.gestionticket.Entities.Role;
import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.UserRepository;
import com.example.gestionticket.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
       User user = new User(registrationDto.getNom(),registrationDto.getPrenom(),registrationDto.getEmail(),
               registrationDto.getPassword(),registrationDto.getAddress(),registrationDto.getTelephone(),
               registrationDto.isActive(),Arrays.asList(new Role("ROLE_USER")));

       return userRepository.save(user);
    }
}
