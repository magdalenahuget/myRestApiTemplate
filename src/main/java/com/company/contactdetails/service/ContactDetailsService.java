package com.company.contactdetails.service;


import com.company.contactdetails.dto.ContactDetailsRequestDto;
import com.company.contactdetails.dto.ContactDetailsResponseDto;
import com.company.contactdetails.dto.GetContactDetailsResponseDto;

public interface ContactDetailsService {

    ContactDetailsResponseDto modifiedContactDetails(Long userId, ContactDetailsRequestDto request);

    ContactDetailsResponseDto createContactDetails(ContactDetailsRequestDto request);

    GetContactDetailsResponseDto getContactDetailsByUserId(Long userId);

    GetContactDetailsResponseDto createAndGetDefaultContactDetails(Long userId);
}
