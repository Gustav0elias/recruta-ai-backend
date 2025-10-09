package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CurriculoResponseDTO(
        Long id,
        UUID uuid,
        String caminhoArquivo,
        Boolean ativo,
        LocalDateTime criadoEm,
        UUID candidatoUuid
) {}