package com.example.gestionticket.services;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.UserRepository;
import com.example.gestionticket.web.dto.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }


    @Override
    public void updateUserProfile(UserUpdateDto updatedUser, byte[] avatar) {
    if (updatedUser.getId() == null) {
        throw new IllegalArgumentException("Updated user must have a valid id");
    }

    // Load the existing user from the database
    User existingUser = userRepository.findById(updatedUser.getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Update the fields of the existing user with the new information only if they have changed
    if (updatedUser.getUsername() != null && !updatedUser.getUsername().equals(existingUser.getUsername())) {
        existingUser.setUsername(updatedUser.getUsername());
    }
    if (updatedUser.getNom() != null && !updatedUser.getNom().equals(existingUser.getNom())) {
        existingUser.setNom(updatedUser.getNom());
    }
    if (updatedUser.getPrenom() != null && !updatedUser.getPrenom().equals(existingUser.getPrenom())) {
        existingUser.setPrenom(updatedUser.getPrenom());
    }
    if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(existingUser.getEmail())) {
        existingUser.setEmail(updatedUser.getEmail());
    }
    if (updatedUser.getPassword() != null && !updatedUser.getPassword().equals(existingUser.getPassword())) {
        existingUser.setPassword(updatedUser.getPassword());
    }
    if (updatedUser.getVille() != null && !updatedUser.getVille().equals(existingUser.getVille())) {
        existingUser.setVille(updatedUser.getVille());
    }
    if (updatedUser.getTelephone() != null && !updatedUser.getTelephone().equals(existingUser.getTelephone())) {
        existingUser.setTelephone(updatedUser.getTelephone());
    }
    if (avatar != null && (existingUser.getAvatar() == null || !Arrays.equals(avatar, existingUser.getAvatar()))) {
        existingUser.setAvatar(avatar);
    }
    // For boolean fields, you can check if the updatedUser's field is different from the existingUser's field
    if (updatedUser.isActive() != existingUser.isActive()) {
        existingUser.setActive(updatedUser.isActive());
    }
    if (updatedUser.getRole() != null && !updatedUser.getRole().equals(existingUser.getRole())) {
        existingUser.setRole(updatedUser.getRole());
    }

    // Save the updated user back to the database
    userRepository.save(existingUser);
}

    public boolean userExists(String username) {

        return userRepository.findByUsername(username) != null;
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}