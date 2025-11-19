package com.ifba.projeto.tcc.application.dto.response;

import com.ifba.projeto.tcc.domain.entity.NivelExperiencia;
import java.time.LocalDateTime;
import java.util.UUID;

public record VagaEstatisticaResponseDTO(
        Long id,
        UUID uuid,
        String titulo,
        NivelExperiencia nivelExperiencia,
        LocalDateTime criadoEm,
        Long quantidadeCandidaturas,
        Double mediaScore
) {}

