package com.company.user.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {

    private String userName;
    private String userEmail;
    private String userPassword;
}
