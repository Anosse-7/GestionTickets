package com.example.gestionticket.services;

import com.example.gestionticket.Entities.User;
import com.example.gestionticket.Repository.UserRepository;
import com.example.gestionticket.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return this.findByUsername(currentUserName);
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setNom(registrationDto.getNom());
        user.setPrenom(registrationDto.getPrenom());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setAddresse(registrationDto.getAddresse());
        user.setTelephone(registrationDto.getTelephone());
        user.setActive(registrationDto.isActive());

        userRepository.save(user);

        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}