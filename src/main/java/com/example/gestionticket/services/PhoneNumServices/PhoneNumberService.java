package com.example.gestionticket.services.PhoneNumServices;

import org.springframework.stereotype.Service;

@Service
public interface PhoneNumberService {

    boolean isValidPhoneNumber(String phoneNumber, String countryCode);
}
