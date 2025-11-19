package com.ifba.projeto.tcc.application.usecase.relatorio;

import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasVagaPorPeriodoResponseDTO;

import java.time.LocalDateTime;

public interface RelatorioCandidaturasVagaPorPeriodoUseCase {
    RelatorioCandidaturasVagaPorPeriodoResponseDTO executar(Long vagaId, LocalDateTime dataInicio, LocalDateTime dataFim);
}

