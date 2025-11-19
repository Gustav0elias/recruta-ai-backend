package com.ifba.projeto.tcc.application.usecase.relatorio;

import com.ifba.projeto.tcc.application.dto.response.DistribuicaoScoreVagaResponseDTO;

public interface RelatorioDistribuicaoScoreVagaUseCase {
    DistribuicaoScoreVagaResponseDTO executar(Long vagaId);
}

