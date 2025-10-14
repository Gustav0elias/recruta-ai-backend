package com.ifba.projeto.tcc.application.dto.response;

public record ScoreResponseDTO(
        Long score,
        String analiseEspecifica,
        String analiseGeral
) {
}
