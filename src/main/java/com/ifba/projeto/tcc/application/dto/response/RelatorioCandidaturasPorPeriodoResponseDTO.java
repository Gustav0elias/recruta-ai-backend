package com.ifba.projeto.tcc.application.dto.response;

import java.time.LocalDateTime;

public record RelatorioCandidaturasPorPeriodoResponseDTO(
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Long totalCandidaturas,
        Double mediaScore,
        Long maiorScore,
        Long menorScore
) {}

