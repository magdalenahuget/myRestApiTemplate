package com.company.email.service;

import com.company.contactdetails.dto.GetContactDetailsResponseDto;
import com.company.contactdetails.service.ContactDetailsService;
import com.company.email.dto.ContactFormRequestDto;
import com.company.email.dto.EmailResponseDto;
import com.company.email.model.EmailLog;
import com.company.email.repository.EmailLogRepository;
import com.company.exception.ResourceNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailLogRepository emailLogRepository;
    private final ContactDetailsService contactDetailsService;


    @Value("${spring.mail.username}")
    private String myAppMainEmail;

    @Value("${mytemplate.app.frontendUrl}")
    private String frontendBaseUrl;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender,
                            EmailLogRepository emailLogRepository,
                            ContactDetailsService contactDetailsService) {
        this.javaMailSender = javaMailSender;
        this.emailLogRepository = emailLogRepository;
        this.contactDetailsService = contactDetailsService;
    }

    @Override
    @Async
    public CompletableFuture<EmailResponseDto> sendContactEmail(ContactFormRequestDto contactFormDto) {
        String recipientEmail = determineRecipientEmail(contactFormDto);
        String senderEmail = contactFormDto.senderEmail();
        String subject = "My Application - Request for an offer ...";
        String content = contactFormDto.emailContent();
        return sendEmail(senderEmail, recipientEmail, subject, content);
    }

    @Override
    @Async
    public CompletableFuture<EmailResponseDto> sendRegistrationEmail(String userName, String userEmail) {
        String subject = "Welcome to my App!";
        String content = "Thank you for registering, " + userName + "!";
        return sendEmail(myAppMainEmail, userEmail, subject, content);
    }

    @Async
    public CompletableFuture<EmailResponseDto> sendPasswordResetEmail(String userEmail, String token) {
        String resetLink = frontendBaseUrl + "/reset-password?token=" + token;

        String subject = "Password Reset Request";
        String content = "To reset your password, click the link below:\n" + resetLink;

        return sendEmail(myAppMainEmail, userEmail, subject, content);
    }

    private CompletableFuture<EmailResponseDto> sendEmail(String sender,
                                                          String recipient,
                                                          String subject,
                                                          String content) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                MimeMessage mimeMessage = createMimeMessage(sender, recipient, subject, content);
                javaMailSender.send(mimeMessage);
                log.info("Email sent successfully to {}", recipient);
                logEmail(recipient, subject, LocalDateTime.now(), true, null);
                return new EmailResponseDto(true);
            } catch (MessagingException e) {
                log.error("Failed to send email to {}: {}", recipient, e.getMessage());
                logEmail(recipient, subject, LocalDateTime.now(), false, e.getMessage());
                return new EmailResponseDto(false);
            }
        });
    }

    private MimeMessage createMimeMessage(String sender, String recipient, String subject, String content)
            throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setFrom(sender);
        message.setReplyTo(sender);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content, true); // HTML content - stworzyć ?
        return mimeMessage;
    }

    private String determineRecipientEmail(ContactFormRequestDto contactFormDto) {
        String recipientEmail = myAppMainEmail;
        if (contactFormDto.offerUserId() != null) {
            GetContactDetailsResponseDto contactDetails = getContactDetailsOrThrow(contactFormDto.offerUserId());
            recipientEmail = contactDetails.getEmail();
        }
        return recipientEmail;
    }

    private GetContactDetailsResponseDto getContactDetailsOrThrow(Long userId) {
        return Optional.ofNullable(contactDetailsService.getContactDetailsByUserId(userId))
                .orElseThrow(() -> new ResourceNotFoundException("No contact details found for user ID: " + userId));
    }

    private void logEmail(String to, String subject, LocalDateTime dateSent, Boolean success, String errorMessage) {
        EmailLog log = new EmailLog();
        log.setToAddress(to);
        log.setSubject(subject);
        log.setDateSent(dateSent);
        log.setSuccess(success);
        log.setErrorMessage(errorMessage);
        emailLogRepository.save(log);
    }
}