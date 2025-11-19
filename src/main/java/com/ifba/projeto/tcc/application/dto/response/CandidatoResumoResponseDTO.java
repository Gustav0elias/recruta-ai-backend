package com.ifba.projeto.tcc.application.dto.response;

import java.util.UUID;

public record CandidatoResumoResponseDTO (
        Long candidatoId,
        UUID uuid,
        String nome,
        String email,
        String telefone
) {
}
