package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.Infra.service.AuthenticatedUserProviderService;
import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasVagaResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioEstatisticasVagaUseCase;
import com.ifba.projeto.tcc.domain.exception.ForbiddenException;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelatorioEstatisticasVagaUseCaseImpl implements RelatorioEstatisticasVagaUseCase {
    private final VagaRepository vagaRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public RelatorioEstatisticasVagaResponseDTO executar(Long vagaId) {
        var usuario = authenticatedUserProvider.getAuthenticatedUser();
        
        var vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga", vagaId));
        
        if (!vaga.getUsuario().getId().equals(usuario.getId())) {
            throw new ForbiddenException("Vaga não pertence ao usuário autenticado");
        }
        
        Long totalCandidaturas = candidaturaRepository.countByVagaId(vagaId);
        Double mediaScore = candidaturaRepository.calcularMediaScoreByVagaId(vagaId);
        Long maiorScore = candidaturaRepository.obterMaiorScoreByVagaId(vagaId);
        Long menorScore = candidaturaRepository.obterMenorScoreByVagaId(vagaId);
        Long candidaturasComScore = candidaturaRepository.countCandidaturasComScoreByVagaId(vagaId);
        Long candidaturasSemScore = candidaturaRepository.countCandidaturasSemScoreByVagaId(vagaId);
        
        return new RelatorioEstatisticasVagaResponseDTO(
                vaga.getId(),
                vaga.getUuid(),
                vaga.getTitulo(),
                totalCandidaturas,
                mediaScore,
                maiorScore,
                menorScore,
                candidaturasComScore,
                candidaturasSemScore
        );
    }
}

