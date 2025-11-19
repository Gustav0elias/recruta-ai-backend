package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CandidatoScoreResponseDTO(
        Long candidatoId,
        UUID candidatoUuid,
        String nome,
        String email,
        Long score,
        LocalDateTime dataCadastro
) {}

