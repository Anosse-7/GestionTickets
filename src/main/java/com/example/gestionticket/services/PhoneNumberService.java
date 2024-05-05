package com.example.gestionticket.services;

import org.springframework.stereotype.Service;

@Service
public interface PhoneNumberService {

    boolean isValidPhoneNumber(String phoneNumber, String countryCode);
}
