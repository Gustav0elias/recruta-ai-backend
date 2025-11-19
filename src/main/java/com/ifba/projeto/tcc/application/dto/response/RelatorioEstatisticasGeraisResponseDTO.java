package com.ifba.projeto.tcc.application.dto.response;

public record RelatorioEstatisticasGeraisResponseDTO(
        Long totalVagas,
        Long totalCandidatos,
        Long totalCandidaturas,
        Long totalHabilidades,
        Double mediaScoreCandidaturas,
        Long vagasComCandidaturas,
        Long vagasSemCandidaturas
) {}

