package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasPorPeriodoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioCandidaturasPorPeriodoUseCase;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RelatorioCandidaturasPorPeriodoUseCaseImpl implements RelatorioCandidaturasPorPeriodoUseCase {
    private final CandidaturaRepository candidaturaRepository;

    @Override
    public RelatorioCandidaturasPorPeriodoResponseDTO executar(LocalDateTime dataInicio, LocalDateTime dataFim) {
        Long totalCandidaturas = candidaturaRepository.countByPeriodo(dataInicio, dataFim);
        Double mediaScore = candidaturaRepository.calcularMediaScorePorPeriodo(dataInicio, dataFim);
        Long maiorScore = candidaturaRepository.obterMaiorScorePorPeriodo(dataInicio, dataFim);
        Long menorScore = candidaturaRepository.obterMenorScorePorPeriodo(dataInicio, dataFim);
        
        return new RelatorioCandidaturasPorPeriodoResponseDTO(
                dataInicio,
                dataFim,
                totalCandidaturas,
                mediaScore,
                maiorScore,
                menorScore
        );
    }
}

