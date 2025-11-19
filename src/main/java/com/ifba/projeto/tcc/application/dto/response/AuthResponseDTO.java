package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;

public record AuthResponseDTO(
        String token,
        String tokenType,
        Long expiresIn,
        LocalDateTime expiresAt,
        UserInfoDTO user
) {
    public record UserInfoDTO(
            Long id,
            String nome,
            String email
    ) {}
}