package com.company.email.controller;

import com.company.email.dto.ContactFormRequestDto;
import com.company.email.dto.EmailResponseDto;
import com.company.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/contact")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<EmailResponseDto> sendContactEmail(@RequestBody ContactFormRequestDto contactFormDto) {
        return emailService.sendContactEmail(contactFormDto);
    }
}