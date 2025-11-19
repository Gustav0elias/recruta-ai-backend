package com.ifba.projeto.tcc.application.usecase.relatorio.impl;

import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreResponseDTO;
import com.ifba.projeto.tcc.application.usecase.relatorio.RelatorioDistribuicaoScoreUseCase;
import com.ifba.projeto.tcc.domain.repository.CandidaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelatorioDistribuicaoScoreUseCaseImpl implements RelatorioDistribuicaoScoreUseCase {
    private final CandidaturaRepository candidaturaRepository;

    @Override
    public DistribuicaoScoreResponseDTO executar() {
        Long faixa0a20 = candidaturaRepository.countByScoreRange(0L, 20L);
        Long faixa21a40 = candidaturaRepository.countByScoreRange(21L, 40L);
        Long faixa41a60 = candidaturaRepository.countByScoreRange(41L, 60L);
        Long faixa61a80 = candidaturaRepository.countByScoreRange(61L, 80L);
        Long faixa81a100 = candidaturaRepository.countByScoreRange(81L, 100L);
        
        return new DistribuicaoScoreResponseDTO(
                faixa0a20,
                faixa21a40,
                faixa41a60,
                faixa61a80,
                faixa81a100
        );
    }
}

