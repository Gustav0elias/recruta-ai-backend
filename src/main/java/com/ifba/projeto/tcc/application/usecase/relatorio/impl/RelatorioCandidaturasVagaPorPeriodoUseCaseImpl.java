package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.Infra.service.AuthenticatedUserProviderService;
import com.ifba.projeto.tcc.application.dto.response.RelatorioCandidaturasVagaPorPeriodoResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioCandidaturasVagaPorPeriodoUseCase;
import com.ifba.projeto.tcc.domain.exception.ForbiddenException;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RelatorioCandidaturasVagaPorPeriodoUseCaseImpl implements RelatorioCandidaturasVagaPorPeriodoUseCase {
    private final VagaRepository vagaRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public RelatorioCandidaturasVagaPorPeriodoResponseDTO executar(Long vagaId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        var usuario = authenticatedUserProvider.getAuthenticatedUser();
        
        var vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga", vagaId));
        
        if (!vaga.getUsuario().getId().equals(usuario.getId())) {
            throw new ForbiddenException("Vaga não pertence ao usuário autenticado");
        }
        
        Long totalCandidaturas = candidaturaRepository.countByVagaIdAndPeriodo(vagaId, dataInicio, dataFim);
        Double mediaScore = candidaturaRepository.calcularMediaScoreByVagaIdAndPeriodo(vagaId, dataInicio, dataFim);
        Long maiorScore = candidaturaRepository.obterMaiorScoreByVagaIdAndPeriodo(vagaId, dataInicio, dataFim);
        Long menorScore = candidaturaRepository.obterMenorScoreByVagaIdAndPeriodo(vagaId, dataInicio, dataFim);
        
        return new RelatorioCandidaturasVagaPorPeriodoResponseDTO(
                vaga.getId(),
                vaga.getTitulo(),
                dataInicio,
                dataFim,
                totalCandidaturas,
                mediaScore,
                maiorScore,
                menorScore
        );
    }
}

