package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CandidaturaResumoResponseDTO(
        UUID uuid,
        LocalDateTime dataCadastro,
        Long score,
        CurriculoResumoResponseDTO curriculo
) {
}
