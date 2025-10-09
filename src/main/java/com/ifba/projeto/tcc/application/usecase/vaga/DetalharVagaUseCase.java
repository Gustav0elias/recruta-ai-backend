package com.ifba.projeto.tcc.application.usecase.vaga;

import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;

public interface DetalharVagaUseCase {
    VagaResponseDTO executar(long vagaId);
}
