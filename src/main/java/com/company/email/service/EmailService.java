package com.company.email.service;


import com.company.email.dto.ContactFormRequestDto;
import com.company.email.dto.EmailResponseDto;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
    CompletableFuture<EmailResponseDto> sendContactEmail(ContactFormRequestDto contactFormDto);

    CompletableFuture<EmailResponseDto> sendRegistrationEmail(String userName, String userEmail);

    CompletableFuture<EmailResponseDto> sendPasswordResetEmail(String userEmail, String token);
}