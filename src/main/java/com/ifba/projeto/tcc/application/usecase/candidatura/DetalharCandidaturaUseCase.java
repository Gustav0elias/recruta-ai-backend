package com.ifba.projeto.tcc.application.usecase.candidatura;

import com.ifba.projeto.tcc.application.dto.response.CandidaturaDetalharResponseDTO;

public interface DetalharCandidaturaUseCase {
    CandidaturaDetalharResponseDTO executar(Long id);
}

