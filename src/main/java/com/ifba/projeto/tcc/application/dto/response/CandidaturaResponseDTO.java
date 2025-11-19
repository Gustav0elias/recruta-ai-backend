package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CandidaturaResponseDTO(
        Long id,
        UUID uuidCandidatura,
        Long score,
        CandidatoResumoResponseDTO candidato
) {
}
