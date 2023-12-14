package com.company.contactdetails.service;

import com.company.contactdetails.dto.ContactDetailsResponseDto;
import com.company.contactdetails.dto.GetContactDetailsResponseDto;
import com.company.contactdetails.model.ContactDetails;
import org.springframework.stereotype.Component;

@Component
public class ContactDetailsMapper {

    ContactDetailsResponseDto mapToCreateContactDetailsResponseDto(ContactDetails contactDetails){
        return ContactDetailsResponseDto.builder()
                .contactId(contactDetails.getId())
                .streetName(contactDetails.getStreetName())
                .streetNumber(contactDetails.getStreetNumber())
                .apartNumber(contactDetails.getApartNumber())
                .postcode(contactDetails.getPostcode())
                .email(contactDetails.getEmail())
                .cityName(contactDetails.getCityName())
                .phoneNumber(contactDetails.getPhoneNumber())
                .webpage(contactDetails.getWebpage())
                .build();
    }

    GetContactDetailsResponseDto mapToGetContactDetailsResponseDto(ContactDetails contactDetails) {
        return GetContactDetailsResponseDto.builder()
                .id(contactDetails.getId())
                .streetName(contactDetails.getStreetName())
                .streetNumber(contactDetails.getStreetNumber())
                .apartNumber(contactDetails.getApartNumber())
                .postcode(contactDetails.getPostcode())
                .cityName(contactDetails.getCityName())
                .email(contactDetails.getEmail())
                .phoneNumber(contactDetails.getPhoneNumber())
                .webpage(contactDetails.getWebpage())
                .build();
    }
}
