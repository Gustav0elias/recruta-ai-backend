package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CandidaturaResponseDTO(
        UUID uuid,
        Long score,
        VagaResumoResponseDTO vaga,
        CurriculoResumoResponseDTO curriculo
) {
}
