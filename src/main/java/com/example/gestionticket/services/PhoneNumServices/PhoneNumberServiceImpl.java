package com.example.gestionticket.services.PhoneNumServices;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl  implements PhoneNumberService{


    @Override
    public boolean isValidPhoneNumber(String phoneNumber , String countryCode) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneNumberUtil.parse(phoneNumber, countryCode);
            return phoneNumberUtil.isValidNumber(numberProto);
        } catch (NumberParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
