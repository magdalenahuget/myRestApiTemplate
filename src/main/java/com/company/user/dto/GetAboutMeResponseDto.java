package com.company.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAboutMeResponseDto {

    private String aboutMe;
}