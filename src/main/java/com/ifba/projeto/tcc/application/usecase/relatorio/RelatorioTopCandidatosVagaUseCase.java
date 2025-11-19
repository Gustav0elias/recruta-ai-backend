package com.ifba.projeto.tcc.application.usecase.relatorio;

import com.ifba.projeto.tcc.application.dto.response.RelatorioTopCandidatosVagaResponseDTO;

public interface RelatorioTopCandidatosVagaUseCase {
    RelatorioTopCandidatosVagaResponseDTO executar(Long vagaId, Integer limite);
}

