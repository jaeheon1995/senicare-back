package com.korit.senicare.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// id중복확인 request body에 대한 DTO

@Getter
@Setter
@NoArgsConstructor
public class IdCheckRequestDto {

    @NotBlank
    private String userId;
    
}
