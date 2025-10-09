package com.ifba.projeto.tcc.application.usecase.vaga;

import com.ifba.projeto.tcc.application.dto.request.AdicionarHabilidadesRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.VagaResponseDTO;

public interface AdicionarHabilidadesUseCase {
    VagaResponseDTO executar (Long vagaId, AdicionarHabilidadesRequestDTO dto);
}
