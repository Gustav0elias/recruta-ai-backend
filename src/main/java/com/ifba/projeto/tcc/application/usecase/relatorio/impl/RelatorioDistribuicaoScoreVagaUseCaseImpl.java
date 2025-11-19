package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.Infra.service.AuthenticatedUserProviderService;
import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreVagaResponseDTO;
import com.ifba.projeto.tcc.application.dto.response.FaixaScoreResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioDistribuicaoScoreVagaUseCase;
import com.ifba.projeto.tcc.domain.exception.ForbiddenException;
import com.ifba.projeto.tcc.domain.exception.ResourceNotFoundException;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import com.ifba.projeto.tcc.domain.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioDistribuicaoScoreVagaUseCaseImpl implements RelatorioDistribuicaoScoreVagaUseCase {
    private final VagaRepository vagaRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final AuthenticatedUserProviderService authenticatedUserProvider;

    @Override
    public DistribuicaoScoreVagaResponseDTO executar(Long vagaId) {
        var usuario = authenticatedUserProvider.getAuthenticatedUser();
        
        var vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga", vagaId));
        
        if (!vaga.getUsuario().getId().equals(usuario.getId())) {
            throw new ForbiddenException("Vaga não pertence ao usuário autenticado");
        }
        
        List<FaixaScoreResponseDTO> distribuicao = new ArrayList<>();
        
        distribuicao.add(new FaixaScoreResponseDTO("0-20", 
                candidaturaRepository.countByVagaIdAndScoreRange(vagaId, 0L, 20L)));
        distribuicao.add(new FaixaScoreResponseDTO("21-40", 
                candidaturaRepository.countByVagaIdAndScoreRange(vagaId, 21L, 40L)));
        distribuicao.add(new FaixaScoreResponseDTO("41-60", 
                candidaturaRepository.countByVagaIdAndScoreRange(vagaId, 41L, 60L)));
        distribuicao.add(new FaixaScoreResponseDTO("61-80", 
                candidaturaRepository.countByVagaIdAndScoreRange(vagaId, 61L, 80L)));
        distribuicao.add(new FaixaScoreResponseDTO("81-100", 
                candidaturaRepository.countByVagaIdAndScoreRange(vagaId, 81L, 100L)));
        
        return new DistribuicaoScoreVagaResponseDTO(
                vaga.getId(),
                vaga.getTitulo(),
                distribuicao
        );
    }
}

