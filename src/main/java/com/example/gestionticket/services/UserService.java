package com.example.gestionticket.services;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

    User save(UserRegistrationDto user);

    User save(User user);

    User findByUsername(String username);

    User getCurrentUser();
}
