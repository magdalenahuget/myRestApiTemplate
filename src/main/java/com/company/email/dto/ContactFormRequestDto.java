package com.company.email.dto;

public record ContactFormRequestDto(Long offerUserId, String senderName, String senderEmail, String emailContent) {
}