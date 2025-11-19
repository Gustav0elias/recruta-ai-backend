package com.ifba.projeto.tcc.application.usecase.relatorio;

import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasPorPeriodoResponseDTO;

import java.time.LocalDateTime;

public interface RelatorioCandidaturasPorPeriodoUseCase {
    RelatorioCandidaturasPorPeriodoResponseDTO executar(LocalDateTime dataInicio, LocalDateTime dataFim);
}

