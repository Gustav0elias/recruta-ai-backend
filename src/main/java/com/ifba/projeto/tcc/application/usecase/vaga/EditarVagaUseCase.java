package com.ifba.projeto.tcc.application.usecase.vaga;

import com.ifba.projeto.tcc.application.dto.request.VagaRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;

public interface EditarVagaUseCase {
    VagaResponseDTO executar(VagaRequestDTO dto, Long id);
}
