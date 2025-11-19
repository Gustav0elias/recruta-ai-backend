package com.ifba.projeto.tcc.application.dto.response;

import java.util.UUID;

public record RelatorioEstatisticasVagaResponseDTO(
        Long id,
        UUID uuid,
        String titulo,
        Long totalCandidaturas,
        Double mediaScore,
        Long maiorScore,
        Long menorScore,
        Long candidaturasComScore,
        Long candidaturasSemScore
) {}

