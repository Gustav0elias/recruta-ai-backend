package com.ifba.projeto.tcc.application.dto.response;

import java.util.UUID;

public record CandidatoResumoResponseDTO (
        UUID uuid,
        String nome,
        String email,
        String telefone
) {
}
