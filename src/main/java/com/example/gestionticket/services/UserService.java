package com.example.gestionticket.services;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.web.dto.UserRegistrationDto;
import com.example.gestionticket.web.dto.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService extends UserDetailsService {

    User save(User user);

    User findByUsername(String username);

    void updateUserProfile(UserUpdateDto updatedUser, byte[] avatar);

    boolean userExists(String username);

}
