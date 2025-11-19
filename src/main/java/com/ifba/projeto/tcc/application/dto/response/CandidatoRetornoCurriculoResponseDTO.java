package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CandidatoRetornoCurriculoResponseDTO(
        Long id,
        UUID uuid,
        String nome,
        String email,
        String telefone,
        String linkedin,
        LocalDateTime criadoEm

) {
}
