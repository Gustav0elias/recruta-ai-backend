package com.ifba.projeto.tcc.application.usecase.relatorio;

import com.ifba.projeto.tcc.application.dto.response.RelatorioEstatisticasVagaResponseDTO;

public interface RelatorioEstatisticasVagaUseCase {
    RelatorioEstatisticasVagaResponseDTO executar(Long vagaId);
}

