package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.Infra.service.AuthenticatedUserProviderService;
import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasGeraisResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioEstatisticasGeraisUseCase;
import com.ifba.projeto.tcc.domain.repository.CandidatoRepository;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import com.ifba.projeto.tcc.domain.repository.HabilidadeRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelatorioEstatisticasGeraisUseCaseImpl implements RelatorioEstatisticasGeraisUseCase {
    private final VagaRepository vagaRepository;
    private final CandidatoRepository candidatoRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final HabilidadeRepository habilidadeRepository;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public RelatorioEstatisticasGeraisResponseDTO executar() {
        var usuario = authenticatedUserProvider.getAuthenticatedUser();
        
        Long totalVagas = vagaRepository.countByUsuarioId(usuario.getId());
        Long totalCandidatos = candidatoRepository.count();
        Long totalCandidaturas = candidaturaRepository.count();
        Long totalHabilidades = habilidadeRepository.count();
        
        Double mediaScoreCandidaturas = candidaturaRepository.calcularMediaScore();
        Long vagasComCandidaturas = vagaRepository.countVagasComCandidaturasByUsuarioId(usuario.getId());
        Long vagasSemCandidaturas = totalVagas - vagasComCandidaturas;
        
        return new RelatorioEstatisticasGeraisResponseDTO(
                totalVagas,
                totalCandidatos,
                totalCandidaturas,
                totalHabilidades,
                mediaScoreCandidaturas,
                vagasComCandidaturas,
                vagasSemCandidaturas
        );
    }
}

