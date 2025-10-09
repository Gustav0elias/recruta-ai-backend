package com.ifba.projeto.tcc.application.usecase.habilidade;

import com.ifba.projeto.tcc.application.dto.request.HabilidadeRequestDTO;
import com.ifba.projeto.tcc.application.dto.response.HabilidadeResponseDTO;

public interface CriarHabilidadeUseCase {
    public HabilidadeResponseDTO executar(HabilidadeRequestDTO habilidade);
}
